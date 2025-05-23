package com.ql.ccs.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.pdf.PdfRenderer;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AssetsUtil {
    private final static String TAG = "AssetsUtil";
    /**
     * 从asset资产文件中获取文本字符串
     * @param context 上下文
     * @param fileName 文件名称
     * @return 返回结果
     */
    public static String getTxtFromAssets(Context context, String fileName) {
        String result = "";
        // 打开资产文件并获得输入流
        try (InputStream is = context.getAssets().open(fileName)) {
            int lenght = is.available();
            byte[] buffer = new byte[lenght];
            is.read(buffer);
            result = new String(buffer, "utf8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /** 从asset资产文件中获取位图对象 */
    public static Bitmap getImgFromAssets(Context context, String fileName) {
        Bitmap bitmap = null;
        // 打开资产文件并获得输入流
        try (InputStream is = context.getAssets().open(fileName)) {
            // 解析输入流得到位图数据
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    /** 把asset资产文件复制到存储卡（若文件已存在就不再复制）
     * @param context 上下文
     * @param assetFile 文件名称
     * @param filePath 文件绝对路径
     * */
    public static void Assets2Sd(Context context, String assetFile, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            copyAssetToStorage(context, assetFile, filePath);
        }
    }
    /** 把asset资产文件复制到存储卡
     * @param context 上下文
     * @param fileAssetPath 文件名称
     * @param filePath 文件绝对路径
     * */
    public static void copyAssetToStorage(Context context, String fileAssetPath, String filePath) {
        File dir = new File(filePath.substring(0, filePath.lastIndexOf("/"))); //获取文件夹
        if (!dir.exists()) {  // 如果文件夹不存在则新创建文件夹
            dir.mkdir();
        }
        try (InputStream is = context.getAssets().open(fileAssetPath); //是一个用于从应用程序的 assets 文件夹中打开文件的方法
             //   OutputStream os = new FileOutputStream(filePath);
             OutputStream os = Files.newOutputStream(Paths.get(filePath));) {  //将数据写入指定的文件路径 filePath ，即储存卡路径 storage/emulated/0/Android/data/com.QL.CentralControlSystem/files/Download
            byte[] buffer = new byte[1024];
            int len = 0;
            while ( (len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** 把pdf文件转换为图片文件的路径列表 */
    public static List<String> convertPdfToImg(String rootDir, String fileName) {
        List<String> pathList = new ArrayList<>();
        String imgDir = String.format("%s%s/", rootDir, MD5Util.encrypt(fileName));
        File dirFile = new File(imgDir);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        // 打开存储卡里指定路径的PDF文件，并创建PDF渲染器
        try (ParcelFileDescriptor pfd = ParcelFileDescriptor.open(
                new File(rootDir+fileName), ParcelFileDescriptor.MODE_READ_ONLY);
             PdfRenderer pdfRenderer = new PdfRenderer(pfd);) {
            int count = pdfRenderer.getPageCount(); // 获取PDF文件的页数
            Log.d(TAG, "page count=" + count);
            String lastName = String.format("%s%03d.jpg", imgDir, count-1);
            File firstFile = new File(imgDir+"000.jpg");
            File lastFile = new File(lastName);
            boolean isExist = firstFile.exists() && lastFile.exists();
            for (int i = 0; i < count; i++) {
                String imgPath = String.format("%s/%03d.jpg", imgDir, i);
                pathList.add(imgPath);
                if (!isExist) { // 目标图片尚不存在
                    // 打开序号为i的页面
                    PdfRenderer.Page page = pdfRenderer.openPage(i);
                    // 创建该页面的临时位图
                    Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(),
                            Bitmap.Config.ARGB_8888);
                    bitmap.eraseColor(Color.WHITE); // 将临时位图洗白
                    // 渲染该PDF页面并写入到临时位图
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                    BitmapUtil.saveImage(imgPath, bitmap); // 把位图对象保存为图片文件
                    page.close(); // 关闭该PDF页面
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathList;
    }
    /** 把资产文件转换为图片路径列表
     * @param context 上下文
     * @param assetFileName 文件名称
     * */
    public static List<String> getPathListFromPdf(Context context, String assetFileName) {
        ///  storage/emulated/0/Android/data/com.QL.CentralControlSystem/files/Download
        String dir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/pdf/";//得到 存储卡 路径
        String filePath = dir + assetFileName; //文件在储存卡中的绝对路径
        Log.d(TAG, "filePath="+filePath);
        // 无法直接从asset目录读取PDF文件，只能先把PDF文件复制到存储卡，再从存储卡读取PDF
        AssetsUtil.Assets2Sd(context, assetFileName, filePath);
        return AssetsUtil.convertPdfToImg(dir, assetFileName);
    }
}
