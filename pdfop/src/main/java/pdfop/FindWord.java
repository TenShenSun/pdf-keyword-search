package pdfop;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FindWord {

    public static ArrayList<String> fileNameList = new ArrayList<>();


    /**
     * judge whether a keyword is contained in a string
     * @param content
     * @param str
     * @return
     */
    public static boolean isExistWord(String content,String str){
        String pattern = "[\\d\\D]*"+ str +"[\\d\\D]*";
        boolean isMatch = Pattern.matches(pattern, content);
        return isMatch;
    }


    /**
     * find keyWord from file and save result to file
     * @param basePath
     * @param fileName
     * @param keyWord
     * @return
     */
    public static String findKeyWordFromFile(String basePath, String fileName, String keyWord){

        String filePath = basePath + "/" +fileName;

        String pageContent = "";
        try {
            PdfReader reader = new PdfReader(filePath);
            int pageNum = reader.getNumberOfPages();
            for(int i=1;i<=pageNum;i++){
                //pageContent += PdfTextExtractor.getTextFromPage(reader, i);//读取第i页的文档内容
                pageContent = PdfTextExtractor.getTextFromPage(reader, i);//读取第i页的文档内容
                if(isExistWord(pageContent,keyWord)){
                    String str = fileName + ":" + i + "页";
                    System.out.println(fileName + ":" + i + "页");
                    return str;
                    //System.out.println(fileName);
                }
            }
            //writer.write(pageContent);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //writer.close();
        }
        return null;
    }


    /**
     * read all files of file
     * @param path
     * @param fileName
     */
    public static void getAllFileName(String path, ArrayList<String> fileName)
    {
        File file = new File(path);
        File [] files = file.listFiles();
        String [] names = file.list();
        if(names == null){
            return;
        }else{
            for (String name:names
                 ) {
                if(filterPDFFileName(name)){
                    fileName.add(name);
                }
            }
        }
        /*if(names != null)
            fileName.addAll(Arrays.asList(names));*/

        /*for(File a:files)
        {
            if(a.isDirectory())
            {
                getAllFileName(a.getAbsolutePath(),fileName);
            }
        }*/
    }



    /**
     * filter filename by rules
     * @param fileName
     * @return
     */
    public static boolean filterPDFFileName(String fileName){
        if(fileName.endsWith(".PDF")||fileName.endsWith(".pdf")){
            return true;
        }else{
            return false;
        }
    }


    /**
     * write the file
     * @param filePath
     * @param result
     */
    public static void save(String filePath, String result){
        File file = new File(filePath);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {

            if (!file.exists()) {
                file.createNewFile();//如果文件不存在，就创建该文件
                fos = new FileOutputStream(file);//首次写入获取
            } else {
                //如果文件已存在，那么就在文件末尾追加写入
                fos = new FileOutputStream(file, true);//这里构造方法多了一个参数true,表示在文件末尾追加写入
            }

            osw = new OutputStreamWriter(fos, "UTF-8");//指定以UTF-8格式写入文件
            osw.write(result);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                osw.close();
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        //savePdfName();
        String keyWord = "行权日";
        String bashPath = "/Users/suntiansheng/IdeaProjects/pdfop/mulu/";
        getAllFileName(bashPath,fileNameList);
        StringBuffer sb = new StringBuffer();
        for (String fileName:fileNameList
             ) {
            String str = findKeyWordFromFile(bashPath, fileName, keyWord);
            if(str!=null){
                sb.append(str + "\n");
            }

        }
        save("/Users/suntiansheng/IdeaProjects/pdfop/mulu/1.txt", sb.toString());
    }
}
