package info.hieudt.scraper.fpts.util;

import info.hieudt.scraper.fpts.domain.SanGiaoDich;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final int MAX_ATTEMPTS = 3;

    private static boolean isPageContainsData(Document document) {
        Element table = document.select("table#FPTSQuote").first();
        return table.getElementsByTag("tr").size() > 3;
    }

    private static Document fetch(String url, Map<String, String> params) throws Exception {
        Connection connection = Jsoup.connect(url);
        connection.data(params);
        connection.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36");
        connection.timeout(60000);

        Document document = null;
        Exception error = null;
        int attempt = 1;

        while (attempt <= MAX_ATTEMPTS) {
            try {
                document = connection.get();
                break;
            } catch (Exception e) {
                attempt++;
                error = e;
            }
        }

        if (error != null) {
            throw error;
        }

        return document;
    }

    private static void save(String fileName, String data) throws IOException {
        File file = new File(fileName);
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Cannot create dir " + parentFile);
        }
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Cannot create file " + fileName);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()))) {
            if (StringUtil.isBlank(data)) {
                System.out.println("WARNING: Empty file saved " + file.getAbsolutePath());
            }
            bufferedWriter.write(data);
        }
    }

    public static void downloadDuLieuLichSu(String url, Map<SanGiaoDich, String[]> danhSachMaChungKhoan, String outputDir) {
        int totalPages = 0;
        String outputFile;

        List<String> errorRequests = new ArrayList<>();

        Map<String, String> params = new HashMap<>();
        params.put("a", "2");
        params.put("t", "2");
        params.put("b", "01/01/2007");
        params.put("e", "30/11/2014");

        System.out.println("Downloading DU LIEU LICH SU: " + url);

        for (SanGiaoDich sanGiaoDich : danhSachMaChungKhoan.keySet()) {
            params.put("c", sanGiaoDich.getValue() + "");

            System.out.println("[" + sanGiaoDich + "]");

            for (String maChungKhoan : danhSachMaChungKhoan.get(sanGiaoDich)) {
                int pageNumber = 1;

                params.put("s", maChungKhoan);

                System.out.print(maChungKhoan);

                while (true) {
                    params.put("p", pageNumber + "");

                    try {
                        Document document = fetch(url, params);
                        if (isPageContainsData(document)) {
                            outputFile = outputDir + "/" + sanGiaoDich + "/" + maChungKhoan + "/page" + pageNumber + ".html";

                            String html = document.html();
                            if (StringUtil.isBlank(html)) {
                                System.out.println("\n==> Warning: File " + outputFile + " has no data");
                            }

                            save(outputFile, html);

                            System.out.print("..." + pageNumber);
                            pageNumber++;
                            totalPages++;
                        } else {
                            System.out.println();
                            break;
                        }
                    } catch (Exception e) {
                        errorRequests.add(url + " | " + params);
                        break;
                    }
                }
            }
        }

        System.out.println("Completed! Total pages downloaded: " + totalPages);
        if (errorRequests.size() > 0) {
            errorRequests.forEach(System.out::println);
        }
    }

    public static void downloadThongKeIndex(String url, String outputDir) {
        int totalPages = 0;
        int pageNumber = 0;
        String outputFile;

        Map<String, String> params = new HashMap<>();
        params.put("a", "1");
        params.put("t", "0");
        params.put("b", "01/01/2007");
        params.put("e", "30/11/2014");

        System.out.println("Downloading THONG KE INDEX: " + url);

        for (SanGiaoDich sanGiaoDich : SanGiaoDich.values()) {
            params.put("c", sanGiaoDich.getValue() + "");
            pageNumber = 1;

            System.out.print(sanGiaoDich);

            while (true) {
                params.put("p", pageNumber + "");

                try {
                    Document document = fetch(url, params);
                    if (isPageContainsData(document)) {
                        outputFile = outputDir + "/" + sanGiaoDich + "/page" + pageNumber + ".html";

                        String html = document.html();
                        if (StringUtil.isBlank(html)) {
                            System.out.println("\n==> Warning: File " + outputFile + " has no data");
                        }

                        save(outputFile, html);

                        System.out.print("..." + pageNumber);
                        pageNumber++;
                        totalPages++;
                    } else {
                        System.out.println();
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("\n----------------------");
                    System.out.println("An error occurred with parameters");
                    System.out.println(params);
                    System.out.println("----------------------");
                    break;
                }
            }
        }
        System.out.println("Completed! Total pages downloaded: " + totalPages);
    }
}
