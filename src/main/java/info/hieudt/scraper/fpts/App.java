package info.hieudt.scraper.fpts;

import info.hieudt.scraper.fpts.domain.*;
import info.hieudt.scraper.fpts.model.*;
import info.hieudt.scraper.fpts.util.DataUtil;
import info.hieudt.scraper.fpts.util.HibernateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hibernate.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class App {
    private static final String BASE_DIR = "data";
    private static final String THONG_KE_INDEX_DIR = BASE_DIR + "/thong-ke-index";
    private static final String DU_LIEU_LICH_DIR = BASE_DIR + "/du-lieu-lich-su";
    private static final String THONG_KE_GIA_DIR = DU_LIEU_LICH_DIR + "/thong-ke-gia";
    private static final String THONG_KE_DAT_LENH_DIR = DU_LIEU_LICH_DIR + "/thong-ke-dat-lenh";
    private static final String GIAO_DICH_KHOP_LENH_DIR = DU_LIEU_LICH_DIR + "/giao-dich-khop-lenh";
    private static final String GIAO_DICH_THOA_THUAN_DIR = DU_LIEU_LICH_DIR + "/giao-dich-thoa-thuan";


    public static void main(String[] args) {
        Instant start = Instant.now();

        // step 1
        // downloadData();

        // step 2
        // parseThongKeIndex();
        // parseThongKeGia();
        // parseThongKeDatLenh();
        // parseGiaoDichKhopLenh();

        // step 3
        // generateDuLieuThiTruongTheoNgay();
        // generateDuLieuThiTruongTheoThang();
        // generateDuLieuThiTruongTheoQuy();
        // generateDuLieuThiTruongTheoNam();
        // generateDuLieuCoPhieu();
        // generateBienTheoNgay();
        // generateBienTheoThang();
        // generateBienTheoQuy();
        // generateBienTheoNam();

        // step 4
        // exportDuLieuThiTruongTheoNgay();
        // exportDuLieuThiTruongTheoThang();
        // exportDuLieuThiTruongTheoQuy();
        // exportDuLieuThiTruongTheoNam();
        // exportDuLieuCoPhieu();
        // exportBienTheoNgay();
        exportBienTheoThang();
        // exportBienTheoQuy();
        // exportBienTheoNam();

        HibernateUtil.shutdown();

        Instant end = Instant.now();
        System.out.println("Finish. " + Duration.between(start, end));
    }

    private static void exportDuLieuThiTruongTheoNgay() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            ScrollableResults results = session.createQuery("from DuLieuThiTruongTheoNgay order by ngay desc")
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Du Lieu Thi Truong Theo Ngay");
            DataFormat dataFormat = workbook.createDataFormat();

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mm-yyyy"));

            CellStyle numberCellStyle1 = workbook.createCellStyle();
            numberCellStyle1.setDataFormat(dataFormat.getFormat("#,##0"));

            int rowIndex = 0;

            Row row = sheet.createRow(rowIndex);
            Cell cell = null;

            List<String> headerColumns = Arrays.asList("Ngay",
                    "HOSE Index", "HOSE Tong gia tri giao dich", "HOSE Tong khoi luong giao dich", "HOSE R",
                    "HNX Index", "HNX Tong gia tri giao dich", "HNX Tong khoi luong giao dich", "HNX R",
                    "VN30 Index", "VN30 Tong gia tri giao dich", "VN30 Tong khoi luong giao dich", "VN30 R");

            for (int i = 0; i < headerColumns.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(headerColumns.get(i));
            }

            while (results.next()) {
                rowIndex++;
                DuLieuThiTruongTheoNgay duLieuThiTruongTheoNgay = (DuLieuThiTruongTheoNgay) results.get(0);

                row = sheet.createRow(rowIndex);

                int columnIndex = -1;

                // ngay
                cell = row.createCell(++columnIndex);
                cell.setCellStyle(dateCellStyle);
                cell.setCellValue(duLieuThiTruongTheoNgay.getNgay());

                // hose - index
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getHoseIndex() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getHoseIndex());
                }

                // hose - gia tri giao dich
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getHoseTongGiaTriGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getHoseTongGiaTriGiaoDich());
                    cell.setCellStyle(numberCellStyle1);
                }

                // hose - khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getHoseTongKhoiLuongGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getHoseTongKhoiLuongGiaoDich());
                    cell.setCellStyle(numberCellStyle1);
                }

                // hose - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getHoseR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getHoseR());
                }

                // hnx - index
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getHnxIndex() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getHnxIndex());
                }

                // hnx - gia tri giao dich
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getHnxTongGiaTriGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getHnxTongGiaTriGiaoDich());
                    cell.setCellStyle(numberCellStyle1);
                }

                // hnx - khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getHnxTongKhoiLuongGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getHnxTongKhoiLuongGiaoDich());
                    cell.setCellStyle(numberCellStyle1);
                }

                // hnx - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getHnxR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getHnxR());
                }

                // vn30 - index
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getVn30Index() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getVn30Index());
                }

                // vn30 - gia tri giao dich
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getVn30TongGiaTriGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getVn30TongGiaTriGiaoDich());
                    cell.setCellStyle(numberCellStyle1);
                }

                // vn30 - khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getVn30TongKhoiLuongGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getVn30TongKhoiLuongGiaoDich());
                    cell.setCellStyle(numberCellStyle1);
                }

                // vn30 - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNgay.getVn30R() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNgay.getVn30R());
                }
            }

            for (int i = 0; i < headerColumns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream("excel/du.lieu.thi.truong.theo.ngay.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void exportDuLieuThiTruongTheoThang() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            ScrollableResults results = session.createQuery("from DuLieuThiTruongTheoThang order by nam desc, thang desc")
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Du Lieu Thi Truong Theo Thang");
            DataFormat dataFormat = workbook.createDataFormat();

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mm-yyyy"));

            CellStyle numberCellStyle1 = workbook.createCellStyle();
            numberCellStyle1.setDataFormat(dataFormat.getFormat("#,##0"));

            int rowIndex = 0;

            Row row = sheet.createRow(rowIndex);
            Cell cell = null;

            List<String> headerColumns = Arrays.asList("Thang", "HOSE R", "HNX R", "VN30 R");

            for (int i = 0; i < headerColumns.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(headerColumns.get(i));
            }

            while (results.next()) {
                rowIndex++;
                DuLieuThiTruongTheoThang duLieuThiTruongTheoThang = (DuLieuThiTruongTheoThang) results.get(0);

                row = sheet.createRow(rowIndex);

                int columnIndex = -1;

                // thang
                cell = row.createCell(++columnIndex);
                cell.setCellStyle(dateCellStyle);
                cell.setCellValue(duLieuThiTruongTheoThang.getNamThang().getNam() + "-" + duLieuThiTruongTheoThang.getNamThang().getThang());

                // hose - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoThang.getHoseR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoThang.getHoseR());
                }

                // hnx - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoThang.getHnxR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoThang.getHnxR());
                }

                // vn30 - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoThang.getVn30R() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoThang.getVn30R());
                }
            }

            for (int i = 0; i < headerColumns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream("excel/du.lieu.thi.truong.theo.thang.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void exportDuLieuThiTruongTheoQuy() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            ScrollableResults results = session.createQuery("from DuLieuThiTruongTheoQuy order by nam desc, quy desc")
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Du Lieu Thi Truong Theo Quy");
            DataFormat dataFormat = workbook.createDataFormat();

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mm-yyyy"));

            CellStyle numberCellStyle1 = workbook.createCellStyle();
            numberCellStyle1.setDataFormat(dataFormat.getFormat("#,##0"));

            int rowIndex = 0;

            Row row = sheet.createRow(rowIndex);
            Cell cell = null;

            List<String> headerColumns = Arrays.asList("Quy", "HOSE R", "HNX R", "VN30 R");

            for (int i = 0; i < headerColumns.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(headerColumns.get(i));
            }

            while (results.next()) {
                rowIndex++;
                DuLieuThiTruongTheoQuy duLieuThiTruongTheoQuy = (DuLieuThiTruongTheoQuy) results.get(0);

                row = sheet.createRow(rowIndex);

                int columnIndex = -1;

                // quy
                cell = row.createCell(++columnIndex);
                cell.setCellStyle(dateCellStyle);
                cell.setCellValue(duLieuThiTruongTheoQuy.getNamQuy().getNam() + "-" + duLieuThiTruongTheoQuy.getNamQuy().getQuy());

                // hose - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoQuy.getHoseR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoQuy.getHoseR());
                }

                // hnx - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoQuy.getHnxR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoQuy.getHnxR());
                }

                // vn30 - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoQuy.getVn30R() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoQuy.getVn30R());
                }
            }

            for (int i = 0; i < headerColumns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream("excel/du.lieu.thi.truong.theo.quy.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void exportDuLieuThiTruongTheoNam() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            ScrollableResults results = session.createQuery("from DuLieuThiTruongTheoNam order by nam desc")
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Du Lieu Thi Truong Theo Nam");
            DataFormat dataFormat = workbook.createDataFormat();

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mm-yyyy"));

            CellStyle numberCellStyle1 = workbook.createCellStyle();
            numberCellStyle1.setDataFormat(dataFormat.getFormat("#,##0"));

            int rowIndex = 0;

            Row row = sheet.createRow(rowIndex);
            Cell cell = null;

            List<String> headerColumns = Arrays.asList("Nam", "HOSE R", "HNX R", "VN30 R");

            for (int i = 0; i < headerColumns.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(headerColumns.get(i));
            }

            while (results.next()) {
                rowIndex++;
                DuLieuThiTruongTheoNam duLieuThiTruongTheoNam = (DuLieuThiTruongTheoNam) results.get(0);

                row = sheet.createRow(rowIndex);

                int columnIndex = -1;

                // nam
                cell = row.createCell(++columnIndex);
                cell.setCellStyle(dateCellStyle);
                cell.setCellValue(duLieuThiTruongTheoNam.getNam());

                // hose - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNam.getHoseR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNam.getHoseR());
                }

                // hnx - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNam.getHnxR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNam.getHnxR());
                }

                // vn30 - R
                cell = row.createCell(++columnIndex);
                if (duLieuThiTruongTheoNam.getVn30R() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuThiTruongTheoNam.getVn30R());
                }
            }

            for (int i = 0; i < headerColumns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream("excel/du.lieu.thi.truong.theo.nam.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void exportDuLieuCoPhieu() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            ScrollableResults results = session.createQuery("from DuLieuCoPhieu d order by d.pk.maChungKhoan asc, d.pk.ngay desc")
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Du Lieu Co Phieu");
            DataFormat dataFormat = workbook.createDataFormat();

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mm-yyyy"));

            CellStyle numberCellStyle1 = workbook.createCellStyle();
            numberCellStyle1.setDataFormat(dataFormat.getFormat("#,##0"));

            int rowIndex = 0;

            Row row = sheet.createRow(rowIndex);
            Cell cell = null;

            List<String> headerColumns = Arrays.asList(
                    "Ngay", "Ma co phieu", "Gia dong cua", "Gia cao nhat", "Gia thap nhat",
                    "Khoi luong giao dich khop lenh", "Khoi luong giao dich tong cong",
                    "Gia tri giao dich khop lenh", "Gia tri giao dich tong cong",
                    "Gia hoi mua", "Khoi luong",
                    "Gia chao ban", "Khoi luong",
                    "% so huu nuoc ngoai"
            );

            for (int i = 0; i < headerColumns.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(headerColumns.get(i));
            }

            while (results.next()) {
                rowIndex++;
                DuLieuCoPhieu duLieuCoPhieu = (DuLieuCoPhieu) results.get(0);

                row = sheet.createRow(rowIndex);

                int columnIndex = -1;

                // ngay
                cell = row.createCell(++columnIndex);
                cell.setCellStyle(dateCellStyle);
                cell.setCellValue(duLieuCoPhieu.getPk().getNgay());

                // ma co phieu
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(duLieuCoPhieu.getPk().getMaChungKhoan());

                // gia dong cua
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getGiaDongCua() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getGiaDongCua());
                }

                // gia cao nhat
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getGiaCaoNhat() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getGiaCaoNhat());
                }

                // gia thap nhat
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getGiaThapNhat() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getGiaThapNhat());
                }

                // khoi luong giao dich khop lenh
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getKhoiLuongGiaoDichKhopLenh() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellStyle(numberCellStyle1);
                    cell.setCellValue(duLieuCoPhieu.getKhoiLuongGiaoDichKhopLenh());
                }

                // khoi luong giao dich tong cong
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getKhoiLuongGiaoDichTongCong() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellStyle(numberCellStyle1);
                    cell.setCellValue(duLieuCoPhieu.getKhoiLuongGiaoDichTongCong());
                }

                // gia tri giao dich khop lenh
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getGiaTriGiaoDichKhopLenh() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getGiaTriGiaoDichKhopLenh());
                }

                // gia tri giao dich tong cong
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getGiaTriGiaoDichTongCong() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getGiaTriGiaoDichTongCong());
                }

                // gia hoi mua
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getGiaHoiMua() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getGiaHoiMua());
                }

                // khoi luong gia hoi mua
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getKhoiLuongGiaHoiMua() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getKhoiLuongGiaHoiMua());
                }

                // gia chao ban
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getGiaChaoBan() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getGiaChaoBan());
                }

                // khoi luong gia chao ban
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getKhoiLuongGiaChaoBan() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getKhoiLuongGiaChaoBan());
                }

                // % so huu nuoc ngoai
                cell = row.createCell(++columnIndex);
                if (duLieuCoPhieu.getSoHuuNuocNgoai() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(duLieuCoPhieu.getSoHuuNuocNgoai());
                }
            }

            for (int i = 0; i < headerColumns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream("excel/du.lieu.co.phieu.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void exportBienTheoNgay() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            ScrollableResults results = session.createQuery("from BienTheoNgay b order by b.pk.sanGiaoDich asc, b.pk.maChungKhoan asc, b.pk.ngay desc")
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Bien Theo Ngay");
            DataFormat dataFormat = workbook.createDataFormat();

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mm-yyyy"));

            CellStyle numberCellStyle1 = workbook.createCellStyle();
            numberCellStyle1.setDataFormat(dataFormat.getFormat("#,##0"));

            int rowIndex = 0;

            Row row = sheet.createRow(rowIndex);
            Cell cell = null;

            List<String> headerColumns = Arrays.asList(
                    "Ngay", "San", "Ma co phieu", "Ri", "Effspread", "Quospread", "%Quospread", "Amihud", "AdAmihud", "Aminvest", "Depth", "CompositeLiq", "Highlow"
            );

            for (int i = 0; i < headerColumns.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(headerColumns.get(i));
            }

            while (results.next()) {
                rowIndex++;
                BienTheoNgay bienTheoNgay = (BienTheoNgay) results.get(0);

                row = sheet.createRow(rowIndex);

                int columnIndex = -1;

                // ngay
                cell = row.createCell(++columnIndex);
                cell.setCellStyle(dateCellStyle);
                cell.setCellValue(bienTheoNgay.getPk().getNgay());

                // san giao dich
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoNgay.getPk().getSanGiaoDich().toString());

                // ma co phieu
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoNgay.getPk().getMaChungKhoan());

                // Ri
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getR());
                }

                // Effspread
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getEffspread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getEffspread());
                }

                // Quospread
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getQuospread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getQuospread());
                }

                // %Quospread
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getPhanTramQuospread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getPhanTramQuospread());
                }

                // Amihud
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getAmihud() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getAmihud());
                }

                // AdAmihud
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getAdAmihud() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getAdAmihud());
                }

                // Aminvest
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getAminvest() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getAminvest());
                }

                // Depth
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getDepth() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getDepth());
                }

                // CompositeLiq
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getCompositeLiq() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getCompositeLiq());
                }

                // Highlow
                cell = row.createCell(++columnIndex);
                if (bienTheoNgay.getHighlow() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNgay.getHighlow());
                }
            }

            for (int i = 0; i < headerColumns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream("excel/bien.theo.ngay.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void exportBienTheoThang() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            ScrollableResults results = session.createQuery("from BienTheoThang b order by b.pk.sanGiaoDich asc, b.pk.maChungKhoan asc, b.pk.nam desc, b.pk.thang desc")
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Bien Theo Thang");
            DataFormat dataFormat = workbook.createDataFormat();

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mm-yyyy"));

            CellStyle numberCellStyle1 = workbook.createCellStyle();
            numberCellStyle1.setDataFormat(dataFormat.getFormat("#,##0"));

            int rowIndex = 0;

            Row row = sheet.createRow(rowIndex);
            Cell cell = null;

            List<String> headerColumns = Arrays.asList(
                    "Thang", "San", "Ma co phieu", "Tong so ngay", "Ri", "Phuong sai", "Roll", "Effspread", "Quospread", "%Quospread", "Amihud", "AdAmihud", "Aminvest",
                    "Depth", "CompositeLiq", "Zeros", "Zeros2", "Highlow", "NT",
                    "Trung binh khoi luong giao dich", "Trung binh gia tri giao dich", "Tong khoi luong giao dich", "Tong gia tri giao dich"
            );

            for (int i = 0; i < headerColumns.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(headerColumns.get(i));
            }

            while (results.next()) {
                rowIndex++;
                BienTheoThang bienTheoThang = (BienTheoThang) results.get(0);

                row = sheet.createRow(rowIndex);

                int columnIndex = -1;

                // thang
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoThang.getPk().getNam() + "-" + bienTheoThang.getPk().getThang());

                // san giao dich
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoThang.getPk().getSanGiaoDich().toString());

                // ma co phieu
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoThang.getPk().getMaChungKhoan());

                // tong so ngay
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(bienTheoThang.getTongSoNgayGiaoDich());

                // Ri
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getR());
                }

                // Phuong sai
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getPhuongSai() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getPhuongSai());
                }

                // Roll
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getRoll() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getRoll());
                }

                // Effspread
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getEffspread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getEffspread());
                }

                // Quospread
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getQuospread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getQuospread());
                }

                // %Quospread
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getPhanTramQuospread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getPhanTramQuospread());
                }

                // Amihud
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getAmihud() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getAmihud());
                }

                // AdAmihud
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getAdAmihud() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getAdAmihud());
                }

                // Aminvest
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getAminvest() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getAminvest());
                }

                // Depth
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getDepth() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getDepth());
                }

                // CompositeLiq
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getCompositeLiq() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getCompositeLiq());
                }

                // Zeros
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getZeros() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getZeros());
                }

                // Zeros2
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getZeros2() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getZeros2());
                }

                // Highlow
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getHighlow() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getHighlow());
                }

                // NT
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getNt() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getNt());
                }

                // Trung binh khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getTrungBinhKhoiLuongGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getTrungBinhKhoiLuongGiaoDich());
                }

                // Trung binh gia tri giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getTrungBinhGiaTriGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getTrungBinhGiaTriGiaoDich());
                }

                // Tong khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getTongKhoiLuongGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getTongKhoiLuongGiaoDich());
                }

                // Tong khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoThang.getTongGiaTriGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoThang.getTongGiaTriGiaoDich());
                }
            }

            for (int i = 0; i < headerColumns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream("excel/bien.theo.thang.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void exportBienTheoQuy() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            ScrollableResults results = session.createQuery("from BienTheoQuy b order by b.pk.sanGiaoDich asc, b.pk.maChungKhoan asc, b.pk.nam desc, b.pk.quy desc")
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Bien Theo Quy");
            DataFormat dataFormat = workbook.createDataFormat();

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mm-yyyy"));

            CellStyle numberCellStyle1 = workbook.createCellStyle();
            numberCellStyle1.setDataFormat(dataFormat.getFormat("#,##0"));

            int rowIndex = 0;

            Row row = sheet.createRow(rowIndex);
            Cell cell = null;

            List<String> headerColumns = Arrays.asList(
                    "Quy", "San", "Ma co phieu", "Tong so ngay", "Ri", "Phuong sai", "Roll", "Effspread", "Quospread", "%Quospread", "Amihud", "AdAmihud", "Aminvest",
                    "Depth", "CompositeLiq", "Zeros", "Zeros2", "Highlow", "NT",
                    "Trung binh khoi luong giao dich", "Trung binh gia tri giao dich", "Tong khoi luong giao dich", "Tong gia tri giao dich"
            );

            for (int i = 0; i < headerColumns.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(headerColumns.get(i));
            }

            while (results.next()) {
                rowIndex++;
                BienTheoQuy bienTheoQuy = (BienTheoQuy) results.get(0);

                row = sheet.createRow(rowIndex);

                int columnIndex = -1;

                // quy
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue("Q" + bienTheoQuy.getPk().getQuy() + "/" + bienTheoQuy.getPk().getNam());

                // san giao dich
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoQuy.getPk().getSanGiaoDich().toString());

                // ma co phieu
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoQuy.getPk().getMaChungKhoan());

                // tong so ngay
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(bienTheoQuy.getTongSoNgayGiaoDich());

                // Ri
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getR());
                }

                // Phuong sai
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getPhuongSai() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getPhuongSai());
                }

                // Roll
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getRoll() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getRoll());
                }

                // Effspread
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getEffspread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getEffspread());
                }

                // Quospread
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getQuospread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getQuospread());
                }

                // %Quospread
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getPhanTramQuospread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getPhanTramQuospread());
                }

                // Amihud
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getAmihud() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getAmihud());
                }

                // AdAmihud
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getAdAmihud() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getAdAmihud());
                }

                // Aminvest
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getAminvest() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getAminvest());
                }

                // Depth
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getDepth() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getDepth());
                }

                // CompositeLiq
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getCompositeLiq() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getCompositeLiq());
                }

                // Zeros
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getZeros() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getZeros());
                }

                // Zeros2
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getZeros2() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getZeros2());
                }

                // Highlow
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getHighlow() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getHighlow());
                }

                // NT
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getNt() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getNt());
                }

                // Trung binh khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getTrungBinhKhoiLuongGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getTrungBinhKhoiLuongGiaoDich());
                }

                // Trung binh gia tri giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getTrungBinhGiaTriGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getTrungBinhGiaTriGiaoDich());
                }

                // Tong khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getTongKhoiLuongGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getTongKhoiLuongGiaoDich());
                }

                // Tong khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoQuy.getTongGiaTriGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoQuy.getTongGiaTriGiaoDich());
                }
            }

            for (int i = 0; i < headerColumns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream("excel/bien.theo.quy.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void exportBienTheoNam() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            ScrollableResults results = session.createQuery("from BienTheoNam b order by b.pk.sanGiaoDich asc, b.pk.maChungKhoan asc, b.pk.nam desc")
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Bien Theo Nam");
            DataFormat dataFormat = workbook.createDataFormat();

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd-mm-yyyy"));

            CellStyle numberCellStyle1 = workbook.createCellStyle();
            numberCellStyle1.setDataFormat(dataFormat.getFormat("#,##0"));

            int rowIndex = 0;

            Row row = sheet.createRow(rowIndex);
            Cell cell = null;

            List<String> headerColumns = Arrays.asList(
                    "Nam", "San", "Ma co phieu", "Tong so ngay", "Ri", "Phuong sai", "Roll", "Effspread", "Quospread", "%Quospread", "Amihud", "AdAmihud", "Aminvest",
                    "Depth", "CompositeLiq", "Zeros", "Zeros2", "Highlow", "NT",
                    "Trung binh khoi luong giao dich", "Trung binh gia tri giao dich", "Tong khoi luong giao dich", "Tong gia tri giao dich"
            );

            for (int i = 0; i < headerColumns.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(headerColumns.get(i));
            }

            while (results.next()) {
                rowIndex++;
                BienTheoNam bienTheoNam = (BienTheoNam) results.get(0);

                row = sheet.createRow(rowIndex);

                int columnIndex = -1;

                // nam
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoNam.getPk().getNam());

                // san giao dich
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoNam.getPk().getSanGiaoDich().toString());

                // ma co phieu
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(bienTheoNam.getPk().getMaChungKhoan());

                // tong so ngay
                cell = row.createCell(++columnIndex);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(bienTheoNam.getTongSoNgayGiaoDich());

                // Ri
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getR() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getR());
                }

                // Phuong sai
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getPhuongSai() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getPhuongSai());
                }

                // Roll
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getRoll() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getRoll());
                }

                // Effspread
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getEffspread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getEffspread());
                }

                // Quospread
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getQuospread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getQuospread());
                }

                // %Quospread
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getPhanTramQuospread() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getPhanTramQuospread());
                }

                // Amihud
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getAmihud() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getAmihud());
                }

                // AdAmihud
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getAdAmihud() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getAdAmihud());
                }

                // Aminvest
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getAminvest() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getAminvest());
                }

                // Depth
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getDepth() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getDepth());
                }

                // CompositeLiq
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getCompositeLiq() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getCompositeLiq());
                }

                // Zeros
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getZeros() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getZeros());
                }

                // Zeros2
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getZeros2() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getZeros2());
                }

                // Highlow
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getHighlow() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getHighlow());
                }

                // NT
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getNt() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getNt());
                }

                // Trung binh khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getTrungBinhKhoiLuongGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getTrungBinhKhoiLuongGiaoDich());
                }

                // Trung binh gia tri giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getTrungBinhGiaTriGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getTrungBinhGiaTriGiaoDich());
                }

                // Tong khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getTongKhoiLuongGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getTongKhoiLuongGiaoDich());
                }

                // Tong khoi luong giao dich
                cell = row.createCell(++columnIndex);
                if (bienTheoNam.getTongGiaTriGiaoDich() != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(bienTheoNam.getTongGiaTriGiaoDich());
                }
            }

            for (int i = 0; i < headerColumns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream outputStream = new FileOutputStream("excel/bien.theo.nam.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void generateDuLieuThiTruongTheoNgay() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "DuLieuThiTruongTheoNgay");

            ScrollableResults results = session.createSQLQuery("SELECT a.* " +
                    "FROM ThongKeIndex a " +
                    "INNER JOIN ( " +
                    "    SELECT sanGiaoDich, ngay, max(dot) as dot " +
                    "    FROM ThongKeIndex " +
                    "    GROUP BY sanGiaoDich, ngay " +
                    ") AS b USING (sanGiaoDich, ngay, dot) " +
                    "ORDER BY a.sanGiaoDich ASC, a.ngay ASC, a.dot ASC ")
                    .addEntity(ThongKeIndex.class)
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            DuLieuThiTruongTheoNgay duLieuThiTruongNgayHomTruoc = null;

            while (results.next()) {
                ThongKeIndex thongKeIndex = (ThongKeIndex) results.get(0);
                DuLieuThiTruongTheoNgay duLieuThiTruongNgayHomNay = (DuLieuThiTruongTheoNgay) session.get(DuLieuThiTruongTheoNgay.class, thongKeIndex.getPk().getNgay());

                if (duLieuThiTruongNgayHomNay == null) {
                    duLieuThiTruongNgayHomNay = new DuLieuThiTruongTheoNgay();
                    duLieuThiTruongNgayHomNay.setNgay(thongKeIndex.getPk().getNgay());
                }

                switch (thongKeIndex.getPk().getSanGiaoDich()) {
                    case HNX:
                        duLieuThiTruongNgayHomNay.setHnxIndex(thongKeIndex.getChiSoIndex());
                        duLieuThiTruongNgayHomNay.setHnxTongGiaTriGiaoDich(thongKeIndex.getTongGiaTriGiaoDich());
                        duLieuThiTruongNgayHomNay.setHnxTongKhoiLuongGiaoDich(thongKeIndex.getTongKhoiLuongGiaoDich());

                        if (duLieuThiTruongNgayHomTruoc != null && duLieuThiTruongNgayHomTruoc.getHnxIndex() != null) {
                            Double indexHomNay = duLieuThiTruongNgayHomNay.getHnxIndex();
                            Double indexHomTruoc = duLieuThiTruongNgayHomTruoc.getHnxIndex();
                            duLieuThiTruongNgayHomNay.setHnxR(Math.log(indexHomNay / indexHomTruoc));
                        }

                        break;
                    case HOSE:
                        duLieuThiTruongNgayHomNay.setHoseIndex(thongKeIndex.getChiSoIndex());
                        duLieuThiTruongNgayHomNay.setHoseTongGiaTriGiaoDich(thongKeIndex.getTongGiaTriGiaoDich());
                        duLieuThiTruongNgayHomNay.setHoseTongKhoiLuongGiaoDich(thongKeIndex.getTongKhoiLuongGiaoDich());

                        if (duLieuThiTruongNgayHomTruoc != null && duLieuThiTruongNgayHomTruoc.getHoseIndex() != null) {
                            Double indexHomNay = duLieuThiTruongNgayHomNay.getHoseIndex();
                            Double indexHomTruoc = duLieuThiTruongNgayHomTruoc.getHoseIndex();
                            duLieuThiTruongNgayHomNay.setHoseR(Math.log(indexHomNay / indexHomTruoc));
                        }

                        break;
                    case VN30:
                        duLieuThiTruongNgayHomNay.setVn30Index(thongKeIndex.getChiSoIndex());
                        duLieuThiTruongNgayHomNay.setVn30TongGiaTriGiaoDich(thongKeIndex.getTongGiaTriGiaoDich());
                        duLieuThiTruongNgayHomNay.setVn30TongKhoiLuongGiaoDich(thongKeIndex.getTongKhoiLuongGiaoDich());

                        if (duLieuThiTruongNgayHomTruoc != null && duLieuThiTruongNgayHomTruoc.getVn30Index() != null) {
                            Double indexHomNay = duLieuThiTruongNgayHomNay.getVn30Index();
                            Double indexHomTruoc = duLieuThiTruongNgayHomTruoc.getVn30Index();
                            duLieuThiTruongNgayHomNay.setVn30R(Math.log(indexHomNay / indexHomTruoc));
                        }

                        break;
                }

                session.save(duLieuThiTruongNgayHomNay);

                duLieuThiTruongNgayHomTruoc = duLieuThiTruongNgayHomNay;
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void generateDuLieuThiTruongTheoThang() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "DuLieuThiTruongTheoThang");

            ScrollableResults results = session.createQuery(
                    "select new info.hieudt.scraper.fpts.model.DuLieuThiTruongTheoThang(" +
                            "year(n.ngay)," +
                            "month(n.ngay)," +
                            "sum(n.hoseR)," +
                            "sum(n.hnxR)," +
                            "sum(n.vn30R)) " +
                            "from DuLieuThiTruongTheoNgay n " +
                            "group by year(n.ngay), month(n.ngay)")
                    .setReadOnly(true)
                    .setFetchSize(100)
                    .scroll(ScrollMode.FORWARD_ONLY);

            while (results.next()) {
                DuLieuThiTruongTheoThang duLieuThiTruongTheoThang = (DuLieuThiTruongTheoThang) results.get(0);
                session.save(duLieuThiTruongTheoThang);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void generateDuLieuThiTruongTheoQuy() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "DuLieuThiTruongTheoQuy");

            ScrollableResults results = session.createQuery(
                    "select new info.hieudt.scraper.fpts.model.DuLieuThiTruongTheoQuy(" +
                            "year(n.ngay)," +
                            "quarter(n.ngay)," +
                            "sum(n.hoseR)," +
                            "sum(n.hnxR)," +
                            "sum(n.vn30R)) " +
                            "from DuLieuThiTruongTheoNgay n " +
                            "group by year(n.ngay), quarter(n.ngay)")
                    .setReadOnly(true)
                    .setFetchSize(100)
                    .scroll(ScrollMode.FORWARD_ONLY);

            while (results.next()) {
                DuLieuThiTruongTheoQuy duLieuThiTruongTheoQuy = (DuLieuThiTruongTheoQuy) results.get(0);
                session.save(duLieuThiTruongTheoQuy);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void generateDuLieuThiTruongTheoNam() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "DuLieuThiTruongTheoNam");

            ScrollableResults results = session.createQuery(
                    "select new info.hieudt.scraper.fpts.model.DuLieuThiTruongTheoNam(" +
                            "year(n.ngay)," +
                            "sum(n.hoseR)," +
                            "sum(n.hnxR)," +
                            "sum(n.vn30R)) " +
                            "from DuLieuThiTruongTheoNgay n " +
                            "group by year(n.ngay)")
                    .setReadOnly(true)
                    .setFetchSize(100)
                    .scroll(ScrollMode.FORWARD_ONLY);

            while (results.next()) {
                DuLieuThiTruongTheoNam duLieuThiTruongTheoNam = (DuLieuThiTruongTheoNam) results.get(0);
                session.save(duLieuThiTruongTheoNam);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void generateDuLieuCoPhieu() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "DuLieuCoPhieu");

            String query =
                    "SELECT g.ngay, g.maChungKhoan, g.sanGiaoDich, g.giaDongCua, g.giaCaoNhat, g.giaThapNhat, g.klgdKhopLenh, g.klgdTongCong, g.gtgdKhopLenh, " +
                            "g.gtgdTongCong, l.giaMuaTotNhat, l.khoiLuongMuaTotNhat, l.giaBanTotNhat, l.khoiLuongBanTotNhat, k.phanTramSoHuuNuocNgoai " +
                            "FROM ThongKeGia g " +
                            "LEFT JOIN ThongKeDatLenh l " +
                            "ON g.ngay = l.ngay " +
                            "AND g.maChungKhoan = l.maChungKhoan " +
                            "AND g.sanGiaoDich = l.sanGiaoDich " +
                            "LEFT JOIN GiaoDichKhopLenh k " +
                            "ON g.ngay = k.ngay " +
                            "AND g.maChungKhoan = k.maChungKhoan " +
                            "AND g.sanGiaoDich = k.sanGiaoDich " +
                            "ORDER BY maChungKhoan ASC, ngay DESC";

            ScrollableResults results = session.createSQLQuery(query)
                    .setReadOnly(true)
                    .scroll(ScrollMode.FORWARD_ONLY);

            while (results.next()) {
                Object[] result = results.get();

                Date ngay = (Date) result[0];
                String maChungKhoan = (String) result[1];
                String sanGiaoDichStr = (String) result[2];

                SanGiaoDich sanGiaoDich = null;
                switch (sanGiaoDichStr) {
                    case "HOSE":
                        sanGiaoDich = SanGiaoDich.HOSE;
                        break;
                    case "HNX":
                        sanGiaoDich = SanGiaoDich.HNX;
                        break;
                }

                NgayMaCK ngayMaCK = new NgayMaCK();
                ngayMaCK.setNgay(ngay);
                ngayMaCK.setMaChungKhoan(maChungKhoan);
                ngayMaCK.setSanGiaoDich(sanGiaoDich);

                DuLieuCoPhieu duLieuCoPhieu = new DuLieuCoPhieu();

                duLieuCoPhieu.setPk(ngayMaCK);
                duLieuCoPhieu.setGiaDongCua((Double) result[3]);
                duLieuCoPhieu.setGiaCaoNhat((Double) result[4]);
                duLieuCoPhieu.setGiaThapNhat((Double) result[5]);
                duLieuCoPhieu.setKhoiLuongGiaoDichKhopLenh((Integer) result[6]);
                duLieuCoPhieu.setKhoiLuongGiaoDichTongCong((Integer) result[7]);
                duLieuCoPhieu.setGiaTriGiaoDichKhopLenh((Double) result[8]);
                duLieuCoPhieu.setGiaTriGiaoDichTongCong((Double) result[9]);
                duLieuCoPhieu.setGiaHoiMua((Double) result[10]);
                duLieuCoPhieu.setKhoiLuongGiaHoiMua((Double) result[11]);
                duLieuCoPhieu.setGiaChaoBan((Double) result[12]);
                duLieuCoPhieu.setKhoiLuongGiaChaoBan((Double) result[13]);
                duLieuCoPhieu.setSoHuuNuocNgoai((Double) result[14]);

                session.save(duLieuCoPhieu);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void generateBienTheoNam() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            session.beginTransaction();

            session.createQuery("delete from BienTheoNam").executeUpdate();

            ScrollableResults results = session.createQuery(
                    "select new info.hieudt.scraper.fpts.model.BienTheoNam(" +
                            "year(b.pk.ngay)," +
                            "b.pk.maChungKhoan," +
                            "b.pk.sanGiaoDich," +
                            "sum(b.R)," +
                            "avg(b.effspread)," +
                            "avg(b.quospread)," +
                            "avg(b.phanTramQuospread)," +
                            "avg(b.amihud)," +
                            "avg(b.adAmihud)," +
                            "avg(b.aminvest)," +
                            "avg(b.depth)," +
                            "avg(b.compositeLiq)," +
                            "avg(b.highlow)) " +
                            "from BienTheoNgay b " +
                            "group by b.pk.maChungKhoan, year(b.pk.ngay)")
                    .setReadOnly(true)
                    .setFetchSize(100)
                    .scroll(ScrollMode.FORWARD_ONLY);

            while (results.next()) {
                BienTheoNam bienTheoNam = (BienTheoNam) results.get(0);
                String maChungKhoan = bienTheoNam.getPk().getMaChungKhoan();
                Integer nam = bienTheoNam.getPk().getNam();

                List<Object[]> duLieu4CotCuoi = session.createQuery("select " +
                        "sum(d.khoiLuongGiaoDichTongCong)," +
                        "sum(d.giaTriGiaoDichTongCong)," +
                        "avg(d.khoiLuongGiaoDichTongCong)," +
                        "avg(d.giaTriGiaoDichTongCong) " +
                        "from DuLieuCoPhieu d " +
                        "where d.pk.maChungKhoan = :maChungKhoan " +
                        "and year(d.pk.ngay) = :year ")
                        .setParameter("maChungKhoan", maChungKhoan)
                        .setParameter("year", nam)
                        .list();

                if (duLieu4CotCuoi != null && !duLieu4CotCuoi.isEmpty()) {
                    Object[] duLieu = duLieu4CotCuoi.get(0);
                    bienTheoNam.setTongKhoiLuongGiaoDich((Long) duLieu[0]);
                    bienTheoNam.setTongGiaTriGiaoDich((Double) duLieu[1]);
                    bienTheoNam.setTrungBinhKhoiLuongGiaoDich((Double) duLieu[2]);
                    bienTheoNam.setTrungBinhGiaTriGiaoDich((Double) duLieu[3]);
                }

                List<BienTheoNgay> bienTheoNgayList = session.createQuery("from BienTheoNgay b " +
                        "where b.pk.maChungKhoan = :maChungKhoan " +
                        "and year(b.pk.ngay) = :nam " +
                        "order by b.pk.ngay asc")
                        .setParameter("maChungKhoan", maChungKhoan)
                        .setParameter("nam", nam)
                        .list();

                if (bienTheoNgayList != null && !bienTheoNgayList.isEmpty()) {
                    int tongSoNgay = bienTheoNgayList.size();

                    // 1. Tinh Roll
                    // tim ngay cuoi cung cua nam truoc do ma phai khac NULL
                    BienTheoNgay ngayCuoiNamTruoc = (BienTheoNgay) session.createQuery("from BienTheoNgay b " +
                            "where b.pk.maChungKhoan = :maChungKhoan " +
                            "and b.pk.ngay < :ngay " +
                            "and b.R != null " +
                            "order by b.pk.ngay desc")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("ngay", bienTheoNgayList.get(0).getPk().getNgay())
                            .setFirstResult(0)
                            .setMaxResults(1)
                            .uniqueResult();

                    if (ngayCuoiNamTruoc != null) {
                        Double tongRt_1 = ngayCuoiNamTruoc.getR();

                        for (int i = 0; i < tongSoNgay - 2; i++) {
                            if (bienTheoNgayList.get(i).getR() != null) {
                                tongRt_1 += bienTheoNgayList.get(i).getR();
                            }
                        }

                        Double ERt_1 = tongRt_1 / tongSoNgay;
                        Double ERt = bienTheoNam.getR();
                        Double covTemp = null;

                        for (int i = 0; i < tongSoNgay; i++) {
                            Double Rt = bienTheoNgayList.get(i).getR();
                            Double Rt_1 = (i == 0) ? ngayCuoiNamTruoc.getR() : bienTheoNgayList.get(i - 1).getR();

                            if (Rt != null && Rt_1 != null) {
                                if (covTemp == null) {
                                    covTemp = 0.0;
                                }
                                covTemp += (Rt - ERt) * (Rt_1 - ERt_1);
                            }
                        }
                        if (covTemp != null) {
                            Double cov = covTemp / tongSoNgay;
                            bienTheoNam.setRoll(cov < 0 ? Math.sqrt(-cov) : 0);
                        }
                    }

                    /*
                    13. Phương sai =  (1/N) ∑(Rit – E(Ri,t))2
                     */
                    Double phuongSaiTemp = null;
                    for (BienTheoNgay bienTheoNgay : bienTheoNgayList) {
                        if (bienTheoNgay.getR() != null && bienTheoNam.getR() != null) {
                            if (phuongSaiTemp == null) {
                                phuongSaiTemp = 0.0;
                            }
                            phuongSaiTemp += Math.pow(bienTheoNgay.getR() - bienTheoNam.getR(), 2);
                        }
                    }
                    if (phuongSaiTemp != null) {
                        bienTheoNam.setPhuongSai(phuongSaiTemp / tongSoNgay);
                    }

                    /*
                    Zeros(10)
                    Ký hiệu: Zeros(10) (chỉ tính theo tháng, quý và năm)
                    Zeros(10) = Số ngày giao dịch có Ri = 0 trong tháng (quý, năm)/Số ngày giao dịch trong tháng (quý, năm)
                     */
                    Long soNgayCoRBang0 = (Long) session.createQuery("select count(*) " +
                            "from BienTheoNgay b " +
                            "where b.pk.maChungKhoan = :maChungKhoan " +
                            "and year(b.pk.ngay) = :nam " +
                            "and b.R = 0")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("nam", nam)
                            .uniqueResult();
                    bienTheoNam.setZeros((double) soNgayCoRBang0 / tongSoNgay);

                    /*
                    Zeros2(11)
                    Ký hiệu: Zeros(11) (chỉ tính theo tháng, quý và năm)
                    Zeros(11) = Số ngày giao dịch có (Ri = 0 và Vi > 0) trong tháng (quý, năm)/Số ngày giao dịch trong tháng (quý, năm)
                     */
                    BigInteger soNgayCoRBang0VaVLonHon0 = (BigInteger) session.createSQLQuery("select count(*) " +
                            "from BienTheoNgay b " +
                            "join DuLieuCoPhieu d on b.maChungKhoan = d.maChungKhoan and b.sanGiaoDich = d.sanGiaoDich and b.ngay = d.ngay " +
                            "where b.maChungKhoan = :maChungKhoan " +
                            "and year(b.ngay) = :nam " +
                            "and b.R = 0 " +
                            "and d.giaTriGiaoDichTongCong > 0")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("nam", nam)
                            .uniqueResult();
                    bienTheoNam.setZeros2(soNgayCoRBang0VaVLonHon0.doubleValue() / tongSoNgay);

                    BigInteger soNgayCoVBang0 = (BigInteger) session.createSQLQuery("select count(*) " +
                            "from BienTheoNgay b " +
                            "join DuLieuCoPhieu d on b.maChungKhoan = d.maChungKhoan and b.sanGiaoDich = d.sanGiaoDich and b.ngay = d.ngay " +
                            "where b.maChungKhoan = :maChungKhoan " +
                            "and year(b.ngay) = :nam " +
                            "and d.giaTriGiaoDichTongCong = 0")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("nam", nam)
                            .uniqueResult();
                    bienTheoNam.setNt(soNgayCoVBang0.doubleValue() / tongSoNgay);

                    bienTheoNam.setTongSoNgayGiaoDich(tongSoNgay);

                }

                session.insert(bienTheoNam);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void generateBienTheoQuy() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            session.beginTransaction();

            session.createQuery("delete from BienTheoQuy").executeUpdate();

            ScrollableResults results = session.createQuery(
                    "select new info.hieudt.scraper.fpts.model.BienTheoQuy(" +
                            "year(b.pk.ngay)," +
                            "quarter(b.pk.ngay)," +
                            "b.pk.maChungKhoan," +
                            "b.pk.sanGiaoDich," +
                            "sum(b.R)," +
                            "avg(b.effspread)," +
                            "avg(b.quospread)," +
                            "avg(b.phanTramQuospread)," +
                            "avg(b.amihud)," +
                            "avg(b.adAmihud)," +
                            "avg(b.aminvest)," +
                            "avg(b.depth)," +
                            "avg(b.compositeLiq)," +
                            "avg(b.highlow)) " +
                            "from BienTheoNgay b " +
                            "group by b.pk.maChungKhoan, year(b.pk.ngay), quarter(b.pk.ngay)")
                    .setReadOnly(true)
                    .setFetchSize(100)
                    .scroll(ScrollMode.FORWARD_ONLY);

            while (results.next()) {
                BienTheoQuy bienTheoQuy = (BienTheoQuy) results.get(0);
                String maChungKhoan = bienTheoQuy.getPk().getMaChungKhoan();
                Integer nam = bienTheoQuy.getPk().getNam();
                Integer quy = bienTheoQuy.getPk().getQuy();

                List<Object[]> duLieu4CotCuoi = session.createQuery("select " +
                        "sum(d.khoiLuongGiaoDichTongCong)," +
                        "sum(d.giaTriGiaoDichTongCong)," +
                        "avg(d.khoiLuongGiaoDichTongCong)," +
                        "avg(d.giaTriGiaoDichTongCong) " +
                        "from DuLieuCoPhieu d " +
                        "where d.pk.maChungKhoan = :maChungKhoan " +
                        "and year(d.pk.ngay) = :year " +
                        "and quarter(d.pk.ngay) = :quarter")
                        .setParameter("maChungKhoan", maChungKhoan)
                        .setParameter("year", nam)
                        .setParameter("quarter", quy)
                        .list();

                if (duLieu4CotCuoi != null && !duLieu4CotCuoi.isEmpty()) {
                    Object[] duLieu = duLieu4CotCuoi.get(0);
                    bienTheoQuy.setTongKhoiLuongGiaoDich((Long) duLieu[0]);
                    bienTheoQuy.setTongGiaTriGiaoDich((Double) duLieu[1]);
                    bienTheoQuy.setTrungBinhKhoiLuongGiaoDich((Double) duLieu[2]);
                    bienTheoQuy.setTrungBinhGiaTriGiaoDich((Double) duLieu[3]);
                }

                List<BienTheoNgay> bienTheoNgayList = session.createQuery("from BienTheoNgay b " +
                        "where b.pk.maChungKhoan = :maChungKhoan " +
                        "and year(b.pk.ngay) = :nam " +
                        "and quarter(b.pk.ngay) = :quy " +
                        "order by b.pk.ngay asc")
                        .setParameter("maChungKhoan", maChungKhoan)
                        .setParameter("nam", nam)
                        .setParameter("quy", quy)
                        .list();

                if (bienTheoNgayList != null && !bienTheoNgayList.isEmpty()) {
                    int tongSoNgay = bienTheoNgayList.size();

                    // 1. Tinh Roll
                    // tim ngay cuoi cung cua quy truoc do ma phai khac NULL
                    BienTheoNgay ngayCuoiQuyTruoc = (BienTheoNgay) session.createQuery("from BienTheoNgay b " +
                            "where b.pk.maChungKhoan = :maChungKhoan " +
                            "and b.pk.ngay < :ngay " +
                            "and b.R != null " +
                            "order by b.pk.ngay desc")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("ngay", bienTheoNgayList.get(0).getPk().getNgay())
                            .setFirstResult(0)
                            .setMaxResults(1)
                            .uniqueResult();

                    if (ngayCuoiQuyTruoc != null) {
                        Double tongRt_1 = ngayCuoiQuyTruoc.getR();

                        for (int i = 0; i < tongSoNgay - 2; i++) {
                            if (bienTheoNgayList.get(i).getR() != null) {
                                tongRt_1 += bienTheoNgayList.get(i).getR();
                            }
                        }

                        Double ERt_1 = tongRt_1 / tongSoNgay;
                        Double ERt = bienTheoQuy.getR();
                        Double covTemp = null;

                        for (int i = 0; i < tongSoNgay; i++) {
                            Double Rt = bienTheoNgayList.get(i).getR();
                            Double Rt_1 = (i == 0) ? ngayCuoiQuyTruoc.getR() : bienTheoNgayList.get(i - 1).getR();

                            if (Rt != null && Rt_1 != null) {
                                if (covTemp == null) {
                                    covTemp = 0.0;
                                }
                                covTemp += (Rt - ERt) * (Rt_1 - ERt_1);
                            }
                        }
                        if (covTemp != null) {
                            Double cov = covTemp / tongSoNgay;
                            bienTheoQuy.setRoll(cov < 0 ? Math.sqrt(-cov) : 0);
                        }
                    }

                    /*
                    13. Phương sai =  (1/N) ∑(Rit – E(Ri,t))2
                     */
                    Double phuongSaiTemp = null;
                    for (BienTheoNgay bienTheoNgay : bienTheoNgayList) {
                        if (bienTheoNgay.getR() != null && bienTheoQuy.getR() != null) {
                            if (phuongSaiTemp == null) {
                                phuongSaiTemp = 0.0;
                            }
                            phuongSaiTemp += Math.pow(bienTheoNgay.getR() - bienTheoQuy.getR(), 2);
                        }
                    }
                    if (phuongSaiTemp != null) {
                        bienTheoQuy.setPhuongSai(phuongSaiTemp / tongSoNgay);
                    }

                    /*
                    Zeros(10)
                    Ký hiệu: Zeros(10) (chỉ tính theo tháng, quý và năm)
                    Zeros(10) = Số ngày giao dịch có Ri = 0 trong tháng (quý, năm)/Số ngày giao dịch trong tháng (quý, năm)
                     */
                    Long soNgayCoRBang0 = (Long) session.createQuery("select count(*) " +
                            "from BienTheoNgay b " +
                            "where b.pk.maChungKhoan = :maChungKhoan " +
                            "and year(b.pk.ngay) = :nam " +
                            "and quarter(b.pk.ngay) = :quy " +
                            "and b.R = 0")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("nam", nam)
                            .setParameter("quy", quy)
                            .uniqueResult();
                    bienTheoQuy.setZeros((double) soNgayCoRBang0 / tongSoNgay);

                    /*
                    Zeros2(11)
                    Ký hiệu: Zeros(11) (chỉ tính theo tháng, quý và năm)
                    Zeros(11) = Số ngày giao dịch có (Ri = 0 và Vi > 0) trong tháng (quý, năm)/Số ngày giao dịch trong tháng (quý, năm)
                     */
                    BigInteger soNgayCoRBang0VaVLonHon0 = (BigInteger) session.createSQLQuery("select count(*) " +
                            "from BienTheoNgay b " +
                            "join DuLieuCoPhieu d on b.maChungKhoan = d.maChungKhoan and b.sanGiaoDich = d.sanGiaoDich and b.ngay = d.ngay " +
                            "where b.maChungKhoan = :maChungKhoan " +
                            "and year(b.ngay) = :nam " +
                            "and quarter(b.ngay) = :quy " +
                            "and b.R = 0 " +
                            "and d.giaTriGiaoDichTongCong > 0")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("nam", nam)
                            .setParameter("quy", quy)
                            .uniqueResult();
                    bienTheoQuy.setZeros2(soNgayCoRBang0VaVLonHon0.doubleValue() / tongSoNgay);

                    BigInteger soNgayCoVBang0 = (BigInteger) session.createSQLQuery("select count(*) " +
                            "from BienTheoNgay b " +
                            "join DuLieuCoPhieu d on b.maChungKhoan = d.maChungKhoan and b.sanGiaoDich = d.sanGiaoDich and b.ngay = d.ngay " +
                            "where b.maChungKhoan = :maChungKhoan " +
                            "and year(b.ngay) = :nam " +
                            "and quarter(b.ngay) = :quy " +
                            "and d.giaTriGiaoDichTongCong = 0")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("nam", nam)
                            .setParameter("quy", quy)
                            .uniqueResult();
                    bienTheoQuy.setNt(soNgayCoVBang0.doubleValue() / tongSoNgay);

                    bienTheoQuy.setTongSoNgayGiaoDich(tongSoNgay);

                }

                session.insert(bienTheoQuy);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void generateBienTheoThang() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();

        try {
            session.beginTransaction();

            session.createQuery("delete from BienTheoThang").executeUpdate();

            ScrollableResults results = session.createQuery(
                    "select new info.hieudt.scraper.fpts.model.BienTheoThang(" +
                            "year(b.pk.ngay)," +
                            "month(b.pk.ngay)," +
                            "b.pk.maChungKhoan," +
                            "b.pk.sanGiaoDich," +
                            "sum(b.R)," +
                            "avg(b.effspread)," +
                            "avg(b.quospread)," +
                            "avg(b.phanTramQuospread)," +
                            "avg(b.amihud)," +
                            "avg(b.adAmihud)," +
                            "avg(b.aminvest)," +
                            "avg(b.depth)," +
                            "avg(b.compositeLiq)," +
                            "avg(b.highlow)) " +
                            "from BienTheoNgay b " +
                            "group by b.pk.maChungKhoan, year(b.pk.ngay), month(b.pk.ngay)")
                    .setReadOnly(true)
                    .setFetchSize(100)
                    .scroll(ScrollMode.FORWARD_ONLY);

            while (results.next()) {
                BienTheoThang bienTheoThang = (BienTheoThang) results.get(0);
                String maChungKhoan = bienTheoThang.getPk().getMaChungKhoan();
                Integer nam = bienTheoThang.getPk().getNam();
                Integer thang = bienTheoThang.getPk().getThang();

                List<Object[]> duLieu4CotCuoi = session.createQuery("select " +
                        "sum(d.khoiLuongGiaoDichTongCong)," +
                        "sum(d.giaTriGiaoDichTongCong)," +
                        "avg(d.khoiLuongGiaoDichTongCong)," +
                        "avg(d.giaTriGiaoDichTongCong) " +
                        "from DuLieuCoPhieu d " +
                        "where d.pk.maChungKhoan = :maChungKhoan " +
                        "and year(d.pk.ngay) = :year " +
                        "and month(d.pk.ngay) = :month")
                        .setParameter("maChungKhoan", maChungKhoan)
                        .setParameter("year", nam)
                        .setParameter("month", thang)
                        .list();

                if (duLieu4CotCuoi != null && !duLieu4CotCuoi.isEmpty()) {
                    Object[] duLieu = duLieu4CotCuoi.get(0);
                    bienTheoThang.setTongKhoiLuongGiaoDich((Long) duLieu[0]);
                    bienTheoThang.setTongGiaTriGiaoDich((Double) duLieu[1]);
                    bienTheoThang.setTrungBinhKhoiLuongGiaoDich((Double) duLieu[2]);
                    bienTheoThang.setTrungBinhGiaTriGiaoDich((Double) duLieu[3]);
                }

                List<BienTheoNgay> bienTheoNgayList = session.createQuery("from BienTheoNgay b " +
                        "where b.pk.maChungKhoan = :maChungKhoan " +
                        "and year(b.pk.ngay) = :year " +
                        "and month(b.pk.ngay) = :month " +
                        "order by b.pk.ngay asc")
                        .setParameter("maChungKhoan", maChungKhoan)
                        .setParameter("year", nam)
                        .setParameter("month", thang)
                        .list();

                if (bienTheoNgayList != null && !bienTheoNgayList.isEmpty()) {
                    int tongSoNgay = bienTheoNgayList.size();

                    // 1. Tinh Roll
                    // tim ngay cuoi cung cua thang truoc do ma phai khac NULL
                    BienTheoNgay ngayCuoiThangTruoc = (BienTheoNgay) session.createQuery("from BienTheoNgay b " +
                            "where b.pk.maChungKhoan = :maChungKhoan " +
                            "and b.pk.ngay < :ngay " +
                            "and b.R != null " +
                            "order by b.pk.ngay desc")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("ngay", bienTheoNgayList.get(0).getPk().getNgay())
                            .setFirstResult(0)
                            .setMaxResults(1)
                            .uniqueResult();

                    if (ngayCuoiThangTruoc != null) {
                        Double tongRt_1 = ngayCuoiThangTruoc.getR();

                        for (int i = 0; i < tongSoNgay - 2; i++) {
                            if (bienTheoNgayList.get(i).getR() != null) {
                                tongRt_1 += bienTheoNgayList.get(i).getR();
                            }
                        }

                        Double ERt_1 = tongRt_1 / tongSoNgay;
                        Double ERt = bienTheoThang.getR();
                        Double covTemp = null;

                        for (int i = 0; i < tongSoNgay; i++) {
                            Double Rt = bienTheoNgayList.get(i).getR();
                            Double Rt_1 = (i == 0) ? ngayCuoiThangTruoc.getR() : bienTheoNgayList.get(i - 1).getR();

                            if (Rt != null && Rt_1 != null) {
                                if (covTemp == null) {
                                    covTemp = 0.0;
                                }
                                covTemp += (Rt - ERt) * (Rt_1 - ERt_1);
                            }
                        }
                        if (covTemp != null) {
                            Double cov = covTemp / tongSoNgay;
                            bienTheoThang.setRoll(cov < 0 ? Math.sqrt(-cov) : 0);
                        }
                    }

                    /*
                    13. Phương sai =  (1/N) ∑(Rit – E(Ri,t))2
                     */
                    Double phuongSaiTemp = null;
                    for (BienTheoNgay bienTheoNgay : bienTheoNgayList) {
                        if (bienTheoNgay.getR() != null && bienTheoThang.getR() != null) {
                            if (phuongSaiTemp == null) {
                                phuongSaiTemp = 0.0;
                            }
                            phuongSaiTemp += Math.pow(bienTheoNgay.getR() - bienTheoThang.getR(), 2);
                        }
                    }
                    if (phuongSaiTemp != null) {
                        bienTheoThang.setPhuongSai(phuongSaiTemp / tongSoNgay);
                    }

                    /*
                    Zeros(10)
                    Ký hiệu: Zeros(10) (chỉ tính theo tháng, quý và năm)
                    Zeros(10) = Số ngày giao dịch có Ri = 0 trong tháng (quý, năm)/Số ngày giao dịch trong tháng (quý, năm)
                     */
                    Long soNgayCoRBang0 = (Long) session.createQuery("select count(*) " +
                            "from BienTheoNgay b " +
                            "where b.pk.maChungKhoan = :maChungKhoan " +
                            "and year(b.pk.ngay) = :nam " +
                            "and month(b.pk.ngay) = :thang " +
                            "and b.R = 0")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("nam", nam)
                            .setParameter("thang", thang)
                            .uniqueResult();
                    bienTheoThang.setZeros((double) soNgayCoRBang0 / tongSoNgay);

                    /*
                    Zeros2(11)
                    Ký hiệu: Zeros(11) (chỉ tính theo tháng, quý và năm)
                    Zeros(11) = Số ngày giao dịch có (Ri = 0 và Vi > 0) trong tháng (quý, năm)/Số ngày giao dịch trong tháng (quý, năm)
                     */
                    BigInteger soNgayCoRBang0VaVLonHon0 = (BigInteger) session.createSQLQuery("select count(*) " +
                            "from BienTheoNgay b " +
                            "join DuLieuCoPhieu d on b.maChungKhoan = d.maChungKhoan and b.sanGiaoDich = d.sanGiaoDich and b.ngay = d.ngay " +
                            "where b.maChungKhoan = :maChungKhoan " +
                            "and year(b.ngay) = :nam " +
                            "and month(b.ngay) = :thang " +
                            "and b.R = 0 " +
                            "and d.giaTriGiaoDichTongCong > 0")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("nam", nam)
                            .setParameter("thang", thang)
                            .uniqueResult();
                    bienTheoThang.setZeros2(soNgayCoRBang0VaVLonHon0.doubleValue() / tongSoNgay);

                    BigInteger soNgayCoVBang0 = (BigInteger) session.createSQLQuery("select count(*) " +
                            "from BienTheoNgay b " +
                            "join DuLieuCoPhieu d on b.maChungKhoan = d.maChungKhoan and b.sanGiaoDich = d.sanGiaoDich and b.ngay = d.ngay " +
                            "where b.maChungKhoan = :maChungKhoan " +
                            "and year(b.ngay) = :nam " +
                            "and month(b.ngay) = :thang " +
                            "and d.giaTriGiaoDichTongCong = 0")
                            .setParameter("maChungKhoan", maChungKhoan)
                            .setParameter("nam", nam)
                            .setParameter("thang", thang)
                            .uniqueResult();
                    bienTheoThang.setNt(soNgayCoVBang0.doubleValue() / tongSoNgay);

                    bienTheoThang.setTongSoNgayGiaoDich(tongSoNgay);
                }

                session.insert(bienTheoThang);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void generateBienTheoNgay() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "BienTheoNgay");

            List<String> maChungKhoanList = session.createQuery("select distinct(cp.pk.maChungKhoan) from DuLieuCoPhieu cp").list();

            for (String maChungKhoan : maChungKhoanList) {
                Query query = session.createQuery("from DuLieuCoPhieu cp where cp.pk.maChungKhoan = :maChungKhoan order by cp.pk.ngay asc");
                query.setParameter("maChungKhoan", maChungKhoan);

                List<DuLieuCoPhieu> duLieuCoPhieuList = query.list();
                List<BienTheoNgay> bienTheoNgayList = new ArrayList<>();

                for (int i = 0; i < duLieuCoPhieuList.size(); i++) {
                    DuLieuCoPhieu duLieuCoPhieuHomNay = duLieuCoPhieuList.get(i);
                    BienTheoNgay bienTheoNgay = null;

                    if (i > 0) {
                        DuLieuCoPhieu duLieuCoPhieuHomTruoc = duLieuCoPhieuList.get(i - 1);
                        DuLieuCoPhieu duLieuCoPhieuHomSau = null;

                        if (i < duLieuCoPhieuList.size() - 1) {
                            duLieuCoPhieuHomSau = duLieuCoPhieuList.get(i + 1);
                        }

                        bienTheoNgay = new BienTheoNgay(duLieuCoPhieuHomNay, duLieuCoPhieuHomTruoc, duLieuCoPhieuHomSau);

                        /* Highlow
                        2) Với những cổ phiếu không giao dịch thường xuyên
                        Nếu cổ phiếu chỉ giao dịch 1 lần trong ngày hoặc tất cả các giao dịch đều có cùng mức giá: nếu mức giá ngày t+1 nằm trong khoảng high-low của ngày t thì dùng mức giá high-low của ngày t cho ngày t+1 => lấy S của ngày t
                        Nếu giá high và low của ngày t+1 bằng nhau nhưng nằm ngoài mức giá high-low của ngày t: dùng mức giá high-low của ngày t cho ngày t+1 => lấy S của ngày t
                        Nếu cổ phiếu ko giao dịch và chỉ có giá bid (chào bán) và ask (hỏi mua) khi đóng cửa thì dùng high-low của ngày gần nhất trước đó
                         */
                        Double giaCaoNhat = duLieuCoPhieuHomNay.getGiaCaoNhat();
                        Double giaThapNhat = duLieuCoPhieuHomNay.getGiaThapNhat();
                        Double giaDongCua = duLieuCoPhieuHomNay.getGiaDongCua();

                        if (giaCaoNhat != null && giaThapNhat != null && giaDongCua != null
                                && giaCaoNhat.equals(giaThapNhat)
                                && giaCaoNhat.equals(giaDongCua)
                                && duLieuCoPhieuHomTruoc.getGiaCaoNhat() != null
                                && duLieuCoPhieuHomTruoc.getGiaThapNhat() != null
                                && giaDongCua <= duLieuCoPhieuHomTruoc.getGiaCaoNhat()
                                && giaDongCua >= duLieuCoPhieuHomTruoc.getGiaThapNhat()) {
                            bienTheoNgay.setHighlow(bienTheoNgayList.get(i - 1).getHighlow());
                        }
                    } else {
                        bienTheoNgay = new BienTheoNgay();
                        bienTheoNgay.setPk(duLieuCoPhieuHomNay.getPk());
                    }

                    session.save(bienTheoNgay);
                    bienTheoNgayList.add(bienTheoNgay);
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void parseThongKeIndex() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "ThongKeIndex");

            for (SanGiaoDich sanGiaoDich : SanGiaoDich.values()) {
                File folder = new File(THONG_KE_INDEX_DIR + "/" + sanGiaoDich);
                for (File file : folder.listFiles()) {
                    if (file.isFile() && file.getName().contains(".html")) {
                        Document document = Jsoup.parse(file, "UTF-8");
                        Elements trs = document.select("table#FPTSQuote tr[class]");
                        if (trs.size() > 0) {
                            for (Element tr : trs) {
                                ThongKeIndex thongKeIndex = null;
                                try {
                                    String ngay = tr.child(0).text().trim();
                                    if (!ngay.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                        continue;
                                    }

                                    Byte dot = null;
                                    try {
                                        dot = Byte.parseByte(tr.child(1).text().trim());
                                    } catch (NumberFormatException e) {
                                        dot = 1;
                                    }

                                    Double chiSoIndex = Double.parseDouble(tr.child(2).text().replace(",", ""));
                                    Double tongGiaTriGiaoDich = Double.parseDouble(tr.child(5).text().replace(",", ""));
                                    Double tongKhoiLuongGiaoDich = Double.parseDouble(tr.child(8).text().replace(",", ""));

                                    thongKeIndex = new ThongKeIndex(
                                            new ThongKeIndexId(ngay, sanGiaoDich, dot),
                                            chiSoIndex,
                                            tongGiaTriGiaoDich,
                                            tongKhoiLuongGiaoDich
                                    );

                                    session.save(thongKeIndex);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                } catch (NonUniqueObjectException e) {
                                    System.err.println("NonUniqueObjectException: " + thongKeIndex);
                                }
                            }
                        }
                    }
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void parseThongKeDatLenh() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "ThongKeDatLenh");

            for (SanGiaoDich sanGiaoDich : SanGiaoDich.values()) {
                File sanGiaoDichDir = new File(THONG_KE_DAT_LENH_DIR + "/" + sanGiaoDich);
                if (sanGiaoDichDir.isDirectory()) {
                    for (File chungKhoanDir : sanGiaoDichDir.listFiles()) {
                        if (chungKhoanDir.isDirectory()) {
                            String maChungKhoan = chungKhoanDir.getName();
                            for (File htmlFile : chungKhoanDir.listFiles()) {
                                if (htmlFile.isFile() && htmlFile.getName().contains(".html")) {
                                    _parseThongKeDatLenh(session, sanGiaoDich, maChungKhoan, htmlFile);
                                }
                            }
                        }
                    }
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void parseThongKeGia() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "ThongKeGia");

            for (SanGiaoDich sanGiaoDich : SanGiaoDich.values()) {
                File sanGiaoDichDir = new File(THONG_KE_GIA_DIR + "/" + sanGiaoDich);
                if (sanGiaoDichDir.isDirectory()) {
                    for (File chungKhoanDir : sanGiaoDichDir.listFiles()) {
                        if (chungKhoanDir.isDirectory()) {
                            String maChungKhoan = chungKhoanDir.getName();
                            for (File htmlFile : chungKhoanDir.listFiles()) {
                                if (htmlFile.isFile() && htmlFile.getName().contains(".html")) {
                                    _parseThongKeGia(session, sanGiaoDich, maChungKhoan, htmlFile);
                                }
                            }
                        }
                    }
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void parseGiaoDichKhopLenh() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            HibernateUtil.deleteTable(session, "GiaoDichKhopLenh");

            for (SanGiaoDich sanGiaoDich : SanGiaoDich.values()) {
                File sanGiaoDichDir = new File(GIAO_DICH_KHOP_LENH_DIR + "/" + sanGiaoDich);
                if (sanGiaoDichDir.isDirectory()) {
                    for (File chungKhoanDir : sanGiaoDichDir.listFiles()) {
                        if (chungKhoanDir.isDirectory()) {
                            String maChungKhoan = chungKhoanDir.getName();
                            for (File htmlFile : chungKhoanDir.listFiles()) {
                                if (htmlFile.isFile() && htmlFile.getName().contains(".html")) {
                                    _parseGiaoDichKhopLenh(session, sanGiaoDich, maChungKhoan, htmlFile);
                                }
                            }
                        }
                    }
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void _parseGiaoDichKhopLenh(Session session, SanGiaoDich sanGiaoDich, String maChungKhoan, File htmlFile) throws IOException {
        Document document = Jsoup.parse(htmlFile, "UTF-8");
        Elements trs = document.select("table#FPTSQuote tr[class]");
        if (trs.size() > 0) {
            for (Element tr : trs) {
                String ngay = null;
                Double khoiLuongCkDuocPhepSoHuu = null;
                Double phanTramSoHuuNuocNgoai = null;
                Double khoiLuongCkConDuocPhepMua = null;
                GiaoDichKhopLenh giaoDichKhopLenh = null;
                try {
                    ngay = tr.child(0).text().trim();
                    if (!ngay.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        continue;
                    }

                    if (!tr.child(2).text().trim().equals("")) {
                        khoiLuongCkDuocPhepSoHuu = Double.parseDouble(tr.child(2).text().replace(",", "").trim());
                    }

                    if (!tr.child(3).text().trim().equals("")) {
                        phanTramSoHuuNuocNgoai = Double.parseDouble(tr.child(3).text().replace(",", "").trim());
                    }

                    if (!tr.child(4).text().trim().equals("")) {
                        khoiLuongCkConDuocPhepMua = Double.parseDouble(tr.child(4).text().replace(",", "").trim());
                    }

                    giaoDichKhopLenh = new GiaoDichKhopLenh(
                            new NgayMaCK(ngay, maChungKhoan, sanGiaoDich),
                            khoiLuongCkDuocPhepSoHuu, phanTramSoHuuNuocNgoai, khoiLuongCkConDuocPhepMua
                    );

                    session.save(giaoDichKhopLenh);
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("maChungKhoan = " + maChungKhoan + ", ngay = " + ngay);
                } catch (NonUniqueObjectException e) {
                    System.err.println("NonUniqueObjectException: " + giaoDichKhopLenh);
                }
            }
        }
    }

    private static void _parseThongKeDatLenh(Session session, SanGiaoDich sanGiaoDich, String maChungKhoan, File htmlFile) throws IOException {
        Document document = Jsoup.parse(htmlFile, "UTF-8");
        Elements trs = document.select("table#FPTSQuote tr[class]");
        if (trs.size() > 0) {
            for (Element tr : trs) {
                String ngay = null;
                Double lenhMua = null;
                Double khoiLuongMua = null;
                Double lenhBan = null;
                Double khoiLuongBan = null;
                Double giaMuaTotNhat = null;
                Double khoiLuongMuaTotNhat = null;
                Double giaBanTotNhat = null;
                Double khoiLuongBanTotNhat = null;
                ThongKeDatLenh thongKeDatLenh = null;
                try {
                    ngay = tr.child(0).text().trim();
                    if (!ngay.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        continue;
                    }

                    if (!tr.child(2).text().trim().equals("")) {
                        lenhMua = Double.parseDouble(tr.child(2).text().replace(",", "").trim());
                    }

                    if (!tr.child(3).text().trim().equals("")) {
                        khoiLuongMua = Double.parseDouble(tr.child(3).text().replace(",", "").trim());
                    }

                    if (!tr.child(4).text().trim().equals("")) {
                        lenhBan = Double.parseDouble(tr.child(4).text().replace(",", "").trim());
                    }

                    if (!tr.child(5).text().trim().equals("")) {
                        khoiLuongBan = Double.parseDouble(tr.child(5).text().replace(",", "").trim());
                    }

                    if (!tr.child(10).text().trim().equals("")) {
                        giaMuaTotNhat = Double.parseDouble(tr.child(10).text().replace(",", "").trim());
                    }

                    if (!tr.child(11).text().trim().equals("")) {
                        khoiLuongMuaTotNhat = Double.parseDouble(tr.child(11).text().replace(",", "").trim());
                    }

                    if (!tr.child(12).text().trim().equals("")) {
                        giaBanTotNhat = Double.parseDouble(tr.child(12).text().replace(",", "").trim());
                    }

                    if (!tr.child(13).text().trim().equals("")) {
                        khoiLuongBanTotNhat = Double.parseDouble(tr.child(13).text().replace(",", "").trim());
                    }

                    thongKeDatLenh = new ThongKeDatLenh(
                            new NgayMaCK(ngay, maChungKhoan, sanGiaoDich),
                            lenhMua, lenhBan, khoiLuongMua, khoiLuongBan, giaMuaTotNhat, khoiLuongMuaTotNhat, giaBanTotNhat, khoiLuongBanTotNhat
                    );

                    session.save(thongKeDatLenh);
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("maChungKhoan = " + maChungKhoan + ", ngay = " + ngay);
                } catch (NonUniqueObjectException e) {
                    System.err.println("NonUniqueObjectException: " + thongKeDatLenh);
                }
            }
        }
    }

    private static void _parseThongKeGia(Session session, SanGiaoDich sanGiaoDich, String maChungKhoan, File htmlFile) throws IOException {
        Document document = Jsoup.parse(htmlFile, "UTF-8");
        Elements trs = document.select("table#FPTSQuote tr[class]");
        if (trs.size() > 0) {
            for (Element tr : trs) {
                String ngay = null;
                Double giaThamChieu = null;
                Double giaTran = null;
                Double giaSan = null;
                Double giaMoCua = null;
                Double giaDongCua = null;
                Double giaCaoNhat = null;
                Double giaThapNhat = null;
                ThongKeGia thongKeGia = null;
                try {
                    ngay = tr.child(0).text().trim();
                    if (!ngay.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        continue;
                    }

                    if (!tr.child(2).text().trim().equals("")) {
                        giaThamChieu = Double.parseDouble(tr.child(2).text().replace(",", "").trim());
                    }

                    if (!tr.child(3).text().trim().equals("")) {
                        giaTran = Double.parseDouble(tr.child(3).text().replace(",", "").trim());
                    }

                    if (!tr.child(4).text().trim().equals("")) {
                        giaSan = Double.parseDouble(tr.child(4).text().replace(",", "").trim());
                    }

                    if (!tr.child(5).text().trim().equals("")) {
                        giaMoCua = Double.parseDouble(tr.child(5).text().replace(",", "").trim());
                    }

                    if (!tr.child(6).text().trim().equals("")) {
                        giaDongCua = Double.parseDouble(tr.child(6).text().replace(",", "").trim());
                    }

                    if (!tr.child(7).text().trim().equals("")) {
                        giaCaoNhat = Double.parseDouble(tr.child(7).text().replace(",", "").trim());
                    }

                    if (!tr.child(8).text().trim().equals("")) {
                        giaThapNhat = Double.parseDouble(tr.child(8).text().replace(",", "").trim());
                    }

                    KhoiLuongGiaoDich khoiLuongGiaoDich = new KhoiLuongGiaoDich();
                    khoiLuongGiaoDich.setKhopLenh(Integer.parseInt(tr.child(12).text().replace(",", "")));
                    khoiLuongGiaoDich.setThoaThuan(Integer.parseInt(tr.child(13).text().replace(",", "")));
                    khoiLuongGiaoDich.setTongCong(Integer.parseInt(tr.child(14).text().replace(",", "")));

                    GiaTriGiaoDich giaTriGiaoDich = new GiaTriGiaoDich();
                    giaTriGiaoDich.setKhopLenh(Double.parseDouble(tr.child(15).text().replace(",", "")));
                    giaTriGiaoDich.setThoaThuan(Double.parseDouble(tr.child(16).text().replace(",", "")));
                    giaTriGiaoDich.setTongCong(Double.parseDouble(tr.child(17).text().replace(",", "")));

                    thongKeGia = new ThongKeGia(
                            new NgayMaCK(ngay, maChungKhoan, sanGiaoDich),
                            new Gia(giaThamChieu, giaTran, giaSan, giaMoCua, giaDongCua, giaCaoNhat, giaThapNhat, 0.0),
                            khoiLuongGiaoDich,
                            giaTriGiaoDich
                    );

                    session.save(thongKeGia);
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("maChungKhoan = " + maChungKhoan + ", ngay = " + ngay);
                } catch (NonUniqueObjectException e) {
                    System.err.println("NonUniqueObjectException: " + thongKeGia);
                }
            }
        }
    }

    private static void downloadData() {
        DataUtil.downloadThongKeIndex("http://priceboard.fpts.com.vn/user/stock/thong-ke/", THONG_KE_INDEX_DIR);
        DataUtil.downloadDuLieuLichSu("http://priceboard.fpts.com.vn/user/stock/lich-su/", DataUtil.maChungKhoan, THONG_KE_GIA_DIR);
        DataUtil.downloadDuLieuLichSu("http://priceboard.fpts.com.vn/user/stock/lich-su-lenh/", DataUtil.maChungKhoan, THONG_KE_DAT_LENH_DIR);
        DataUtil.downloadDuLieuLichSu("http://priceboard.fpts.com.vn/user/stock/ndtnn/", DataUtil.maChungKhoan, GIAO_DICH_KHOP_LENH_DIR);
        DataUtil.downloadDuLieuLichSu("http://priceboard.fpts.com.vn/user/stock/ndtnntt/", DataUtil.maChungKhoan, GIAO_DICH_THOA_THUAN_DIR);
    }
}
