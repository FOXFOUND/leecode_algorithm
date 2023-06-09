package idea.find.to.excel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PcTest {
    public static void main(String[] args) throws IOException {

        String path = "/Users/linbowen/Desktop/4.txt";
        String replaceString = "\\(\\d* usages? found\\)";
        String replaceBlack = "    ";
        String fileOriginal = FileUtils.readFileToString(new File(path));
        String removeUsage = fileOriginal.replaceAll(replaceString,"");
        String exceptString = removeUsage.replaceAll(replaceBlack,"\t");
        FileUtils.writeStringToFile(new File(path),exceptString);
        String regexStr = "\t";
        String split = "&";
        String packageName = "";
        String fileName = "";
        String methodName = "";
        List<String> resList = new ArrayList<>();

        List<String> stringList = FileUtils.readLines(new File(path));
        for (int i = 0; i < stringList.size(); i++) {
            String currentLine = stringList.get(i);
            if (StringUtils.isBlank(currentLine)) {
                continue;
            }
            Pattern pattern = Pattern.compile(regexStr);
            Matcher matcher = pattern.matcher(currentLine);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            switch (count) {
                case 3:
                    //包名
                    packageName = StringUtils.trim(currentLine.replaceAll("\t", ""));
                    break;
                case 4:
                    //文件名
                    fileName = StringUtils.trim(currentLine.replaceAll("\t", ""));
                    break;
                case 5:
                    methodName = StringUtils.trim(currentLine.replaceAll("\t", ""));
                    //方法名
                    break;
                case 6:
                    //代码
                    String code = currentLine.replaceAll("\t", "");
                    String[] arr = code.split(" ");
                    String lineNum = arr[0];
                    String codeStr = "";
                    StringBuilder stringBuilder1 = new StringBuilder();
                    for (int j = 1; j < arr.length; j++) {
                        stringBuilder1.append(arr[j]);
                        stringBuilder1.append(" ");
                        codeStr = stringBuilder1.toString();

                    }

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(packageName);
                    stringBuilder.append(".");
                    stringBuilder.append(fileName);
                    stringBuilder.append("#");
                    stringBuilder.append(methodName);
                    stringBuilder.append(split);
                    stringBuilder.append(fileName);
                    stringBuilder.append(":");
                    stringBuilder.append(lineNum);
                    stringBuilder.append(split);
                    stringBuilder.append(codeStr);
                    resList.add(stringBuilder.toString());
                    break;
                default:

            }
        }

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
        }


    }
}