package cn.netinnet.cloudcommon.utils;

import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;


/**
 * Copyright ©厦门网中网软件有限公司.
 *
 * @author here
 * @version 1.0
 * @description 文件相关的字符操作工具类
 * @date 2019/4/26 10:27
 */
public class StringUtilForFile {

    /** 斜杠 */
    public final static String SLASH = "/";
    /** 反斜杠 */
    public final static String BACKSLASH = "\\\\";
    /** 换行 */
    public static final String LINE_SEPARATOR = "\n";

    /** 方法描述
     * @description 获取文件扩展名(不包含.)
     * @param fileName 文件名或文件路径
     * @return java.lang.String
     * @author here
     * @date 2019/4/26 10:16
     */
    static String getFileExtendName(String fileName){
        if ((fileName == null) || ("".equals(fileName))) {
            return "";
        }
        int idx = fileName.lastIndexOf(".");
        if (idx < 0) {
            return "";
        }
        return fileName.substring(idx);
    }

    /** 方法描述
     * @description 获取文件名（不带后缀）
     * @param fileName 文件名
     * @return java.lang.String
     * @author here
     * @date 2019/4/26 10:17
     */
    static String getNameExcludeExtendName(String fileName) {
        if ((fileName == null) || ("".equals(fileName))) {
            return "";
        }
        int idx = fileName.lastIndexOf(".");
        if (idx < 0) {
            return "";
        }
        return fileName.substring(0, idx);
    }

    /** 方法描述
     * @description 替换路径上的所有反斜杠为斜杠
     * @param dir 要被替换的文件路径
     * @param endWithSlash 是否以斜杠结束, 如果是, 将在末尾添加斜杠
     * @param defaultVal 默认值,  当dir为null或为全空字符串时,要设置的默认值
     * @return java.lang.String
     * @author here
     * @date 2019/4/26 11:35
     */
    public static String replaceAllBackslashs(String dir, boolean endWithSlash, String defaultVal) {
        if(StringUtils.isBlank(dir)) {
            dir = defaultVal;
        } else {
            dir.replaceAll(BACKSLASH, SLASH);
            //如果不是以斜杠结尾,则自动加上斜杠
            if (endWithSlash && !dir.endsWith(SLASH)) {
                dir += SLASH;
            }
        }
        return dir;
    }

    /**
     * @Description 判断是否为绝对路径
     * @Param  path
     * @return boolean
     * @Author osp
     * @Date 2019/4/27
     **/
    public static boolean isAbsolutePath(String path) {
        return path.startsWith("/") || path.indexOf(":") > 0;
    }

    public static Document strToDocument(String xmlStr) {
        Document doc;
        StringReader sr = new StringReader(xmlStr);
        InputSource is = new InputSource(sr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new CustomException("生成流程资源文件出错！");
        }
        return doc;
    }

    public static void outputXml(Document doc, String filename) throws FileNotFoundException, TransformerException {
        //将document中的内容写入文件中
        String filePath = filename.substring(0, filename.lastIndexOf(GlobalConstant.FILE_SEPARATOR));
        File f = new File(filePath);
        if(!f.exists()){
            f.mkdirs();
        }
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(filename));
        transformer.transform(source, result);
    }

    public static String getAbsolutePath(String path) {
        if (path.contains(":")) {
            return path;
        }
        return ClassUtils.getDefaultClassLoader().getResource("").getPath() + path;
    }

}
