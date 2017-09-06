package info.hieudt.scraper.cafef.util;

import info.hieudt.scraper.cafef.domain.SanGiaoDich;
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

    public static final Map<SanGiaoDich, String[]> maChungKhoan = new HashMap<>();

    static {
        maChungKhoan.put(SanGiaoDich.HSX, new String[]{
                "AAM", "ABT", "ACC", "ACL", "AGD", "AGF", "AGM", "AGR", "ALP", "ANV", "APC", "ASIAGF", "ASM", "ASP", "ATA", "AVF",
                "BAS", "BBC", "BBT", "BCE", "BCI", "BGM", "BHS", "BIC", "BID", "BMC", "BMI", "BMP", "BRC", "BSI", "BT6", "BTP", "BTT", "BVH",
                "C21", "C32", "C47", "CAV", "CCI", "CCL", "CDC", "CIG", "CII", "CII41401", "CLC", "CLG", "CLL", "CLP", "CLW", "CMG", "CMT",
                "CMV", "CMX", "CNG", "CNT", "COM", "CSG", "CSM", "CTD", "CTG", "CTI", "CYC", "D2D", "DAG", "DCC", "DCL", "DCT", "DHA", "DHC",
                "DHG", "DHM", "DIC", "DIG", "DLG", "DMC", "DPM", "DPR", "DQC", "DRC", "DRH", "DRL", "DSN", "DTA", "DTL", "DTT", "DVD", "DVP",
                "DXG", "DXV", "E1VFVN30", "EIB", "ELC", "EMC", "EVE", "FBT", "FCM", "FCN", "FDC", "FDG", "FLC", "FMC", "FPC", "FPT", "GAS",
                "GDT", "GIL", "GMC", "GMD", "GSP", "GTA", "GTN", "GTT", "HAG", "HAH", "HAI", "HAP", "HAR", "HAS", "HAX", "HBC", "HCM", "HDC",
                "HDG", "HHS", "HLA", "HLG", "HMC", "HOT", "HPG", "HQC", "HRC", "HSG", "HSI", "HT1", "HT2", "HTI", "HTL", "HTV", "HU1", "HU3",
                "HU4", "HVG", "HVX", "ICF", "IDI", "IFS", "IJC", "IMP", "ITA", "ITC", "ITD", "JVC", "KAC", "KBC", "KDC", "KDH", "KHA", "KHP",
                "KMR", "KSA", "KSB", "KSH", "KSS", "KTB", "L10", "LAF", "LBM", "LCG", "LCM", "LGC", "LGL", "LHG", "LIX", "LM8", "LSS", "MAFPF1",
                "MBB", "MCG", "MCP", "MCV", "MDG", "MHC", "MKP", "MPC", "MSN", "MTG", "MWG", "NAV", "NBB", "NCT", "NHS", "NHW", "NKD", "NKG",
                "NLG", "NNC", "NSC", "NTL", "NVN", "NVT", "OGC", "OPC", "PAC", "PAN", "PDN", "PDR", "PET", "PGC", "PGD", "PGI", "PHR", "PHT",
                "PIT", "PJT", "PNC", "PNJ", "POM", "PPC", "PPI", "PRUBF1", "PTB", "PTC", "PTK", "PTL", "PVD", "PVF", "PVT", "PXI", "PXL",
                "PXS", "PXT", "QBS", "QCG", "RAL", "RDP", "REE", "RIC", "SAM", "SAV", "SBA", "SBC", "SBT", "SC5", "SCD", "SEC", "SFC", "SFF",
                "SFG", "SFI", "SGT", "SHI", "SHP", "SII", "SJD", "SJS", "SKG", "SMA", "SMC", "SPM", "SRC", "SRF", "SSC", "SSI", "ST8", "STB",
                "STG", "STT", "SVC", "SVI", "SVT", "SZL", "TAC", "TBC", "TCL", "TCM", "TCO", "TCR", "TDC", "TDH", "TDW", "THG", "TIC", "TIE",
                "TIP", "TIX", "TLG", "TLH", "TMP", "TMS", "TMT", "TNA", "TNC", "TNT", "TPC", "TRA", "TRC", "TRI", "TS4", "TSC", "TTF", "TTP",
                "TV1", "TVS", "TYA", "UDC", "UIC", "VCB", "VCF", "VES", "VFG", "VFMVF1", "VFMVF4", "VFMVFA", "VFMVN30", "VHC", "VHG", "VIC",
                "VID", "VIP", "VIS", "VLF", "VMD", "VNA", "VNE", "VNG", "VNH", "VNI", "VNL", "VNM", "VNS", "VOS", "VPH", "VPK", "VPL", "VRC",
                "VSC", "VSH", "VSI", "VST", "VTB", "VTF", "VTO"
        });
        maChungKhoan.put(SanGiaoDich.HNX, new String[]{
                "AAA", "ACB", "ADC", "AGC", "ALT", "ALV", "AMC", "AME", "AMV", "APG", "API", "APP", "APS", "ARM", "ASA", "AVS", "B82", "BAM",
                "BBS", "BCC", "BDB", "BED", "BHC", "BHT", "BHV", "BII", "BKC", "BLF", "BPC", "BSC", "BST", "BTH", "BTS", "BVG", "BVS", "BXH",
                "C92", "CAN", "CAP", "CCM", "CEO", "CHP", "CIC", "CID", "CJC", "CKV", "CMC", "CMI", "CMS", "CPC", "CSC", "CT6", "CTA", "CTB",
                "CTC", "CTM", "CTN", "CTS", "CTV", "CTX", "CVN", "CVT", "CX8", "D11", "DAC", "DAD", "DAE", "DBC", "DBT", "DC2", "DC4", "DCS",
                "DGC", "DHI", "DHL", "DHP", "DHT", "DID", "DIH", "DL1", "DLR", "DNC", "DNM", "DNP", "DNY", "DPC", "DST", "DXP", "DZM", "E1SSHN30",
                "EBS", "ECI", "EFI", "EID", "FDT", "FIT", "GBS", "GCC", "GFC", "GHA", "GLT", "GMX", "HAD", "HAT", "HBB", "HBE", "HBS", "HCC",
                "HCT", "HDA", "HDO", "HEV", "HGM", "HHC", "HHG", "HHL", "HJS", "HLC", "HLD", "HLY", "HMH", "HNM", "HOM", "HPC", "HPR", "HPS",
                "HSC", "HST", "HTB", "HTC", "HTP", "HUT", "HVT", "ICG", "IDJ", "IDV", "ILC", "INC", "INN", "ITQ", "IVS", "KBT", "KHB", "KHL",
                "KKC", "KLF", "KLS", "KMF", "KMT", "KSD", "KSK", "KSQ", "KST", "KTS", "KTT", "L14", "L18", "L35", "L43", "L44", "L61", "L62",
                "LAS", "LBE", "LCD", "LCS", "LDP", "LHC", "LIG", "LM3", "LM7", "LO5", "LTC", "LUT", "MAC", "MAS", "MAX", "MCC", "MCF", "MCL",
                "MCO", "MDC", "MEC", "MHL", "MIH", "MIM", "MKV", "MNC", "NAG", "NBC", "NBP", "NDF", "NDN", "NDX", "NET", "NFC", "NGC", "NHA",
                "NHC", "NIS", "NLC", "NPS", "NSN", "NST", "NTP", "NVB", "NVC", "OCH", "ONE", "ORS", "PBP", "PCG", "PCT", "PDC", "PEN", "PFL",
                "PGS", "PGT", "PHC", "PHH", "PHS", "PID", "PIV", "PJC", "PLC", "PMC", "PMS", "POT", "PPE", "PPG", "PPP", "PPS", "PRC", "PSC",
                "PSD", "PSE", "PSG", "PSI", "PTI", "PTM", "PTS", "PV2", "PVB", "PVC", "PVE", "PVG", "PVI", "PVL", "PVR", "PVS", "PVV", "PVX",
                "PXA", "QHD", "QNC", "QST", "QTC", "RCL", "RHC", "S12", "S55", "S64", "S74", "S91", "S99", "SAF", "SAP", "SCJ", "SCL", "SCR",
                "SD1", "SD2", "SD4", "SD5", "SD6", "SD7", "SD8", "SD9", "SDA", "SDC", "SDD", "SDE", "SDG", "SDH", "SDN", "SDP", "SDS", "SDT",
                "SDU", "SDY", "SEB", "SED", "SEL", "SFN", "SGC", "SGD", "SGH", "SHA", "SHB", "SHC", "SHN", "SHS", "SIC", "SJ1", "SJC", "SJE",
                "SKS", "SLS", "SME", "SMT", "SNG", "SPI", "SPP", "SQC", "SRA", "SRB", "SSG", "SSM", "SSS", "STC", "STP", "SVN", "SVS", "TAG",
                "TAS", "TBX", "TC6", "TCS", "TCT", "TDN", "TET", "TH1", "THB", "THS", "THT", "THV", "TIG", "TJC", "TKC", "TKU", "TLC", "TMC",
                "TMX", "TNG", "TPH", "TPP", "TSB", "TSM", "TST", "TTB", "TTC", "TTZ", "TV2", "TV3", "TV4", "TVC", "TVD", "TXM", "UNI", "V12",
                "V15", "V21", "VAT", "VBC", "VBH", "VC1", "VC2", "VC3", "VC5", "VC6", "VC7", "VC9", "VCC", "VCG", "VCH", "VCM", "VCR", "VCS",
                "VCV", "VDL", "VDS", "VE1", "VE2", "VE3", "VE4", "VE8", "VE9", "VFR", "VGP", "VGS", "VHL", "VIE", "VIG", "VIT", "VIX", "VKC",
                "VLA", "VMC", "VMG", "VMI", "VNC", "VND", "VNF", "VNN", "VNR", "VNT", "VPC", "VTC", "VTH", "VTL", "VTS", "VTV", "VXB", "WCS",
                "WSS", "XMC", "YSC"
        });
        maChungKhoan.put(SanGiaoDich.UPCOM, new String[]{
                "ABI", "ACE", "ADP", "AMD", "ASD", "BCP", "BHP", "BMJ", "BT1", "BTC", "BTG", "BTW", "BVN", "BWA", "BXD", "CAD", "CEC", "CFC",
                "CI5", "CK8", "CLS", "CMK", "CNC", "CT3", "CTT", "CZC", "D26", "DAP", "DAS", "DBF", "DBM", "DDM", "DDN", "DGT", "DHD", "DLC",
                "DLD", "DLV", "DNF", "DNL", "DNS", "DNT", "DPP", "DTC", "DTH", "DTN", "DTV", "DVC", "DVH", "DXL", "FBA", "GDW", "GER", "GGG",
                "GHC", "GMS", "GTC", "GTH", "H11", "HBD", "HCB", "HCI", "HDM", "HFC", "HFX", "HHA", "HIG", "HPB", "HPL", "HPP", "HPT", "HVC",
                "I40", "ICI", "IHK", "IME", "IMT", "IN4", "ITS", "JSC", "KBE", "KCE", "KSC", "KTL", "LCC", "LKW", "MDF", "MEF", "MIC", "MJC",
                "MMC", "MTC", "MTH", "MTP", "NAS", "NBS", "NBW", "NCS", "ND2", "NDC", "NHN", "NMK", "NNT", "NOS", "NSP", "NT2", "NTB", "NTW",
                "OLC", "PEC", "PFV", "PJS", "PMT", "PNT", "POV", "PRO", "PSB", "PSL", "PSP", "PTD", "PTG", "PTH", "PTP", "PTT", "PVA", "PX1",
                "PXM", "QCC", "QPH", "RDC", "REM", "S27", "S33", "S96", "SBS", "SCC", "SCI", "SCO", "SD3", "SDB", "SDF", "SDI", "SDJ", "SDK",
                "SDV", "SFT", "SGS", "SHV", "SJM", "SMB", "SNC", "SPC", "SPD", "SPH", "SSF", "SSN", "STJ", "STL", "STS", "STU", "STV", "SWC",
                "TBD", "TBT", "TDL", "TDS", "TGP", "TIS", "TL4", "TLT", "TMD", "TMW", "TNB", "TNM", "TNY", "TTG", "TTR", "TVG", "TVP", "UDJ",
                "V11", "VCA", "VCT", "VCX", "VDN", "VDP", "VDT", "VEE", "VFC", "VHF", "VHH", "VIA", "VIN", "VIR", "VKD", "VKP", "VMS", "VNX",
                "VNY", "VQC", "VRG", "VSG", "VSP", "VT1", "VTA", "VTI", "VTJ", "VTX", "WSB", "WTC", "XPH", "YBC"
        });
    }

    private static boolean isPageContainsData(Document document) {
        Element table = document.select("table.GirdTable").first();
        return table.getElementsByTag("tr").size() > 2;
    }

    private static Document fetch(String url, Map<String, String> params) throws Exception {
        Connection connection = Jsoup.connect(url);
        if (params != null) {
            connection.data(params);
        }
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

    private static Document post(String url, Map<String, String> params) throws Exception {
        Connection connection = Jsoup.connect(url);
        connection.data(params);
        connection.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36");
        connection.timeout(60000);

        Document document = null;
        Exception error = null;
        int attempt = 1;

        while (attempt <= MAX_ATTEMPTS) {
            try {
                document = connection.post();
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

    public static void downloadGiaoDichCoDongLonVaNoiBo(Map<SanGiaoDich, String[]> danhSachMaChungKhoan, String outputDir) {
        int totalPages = 0;
        String outputFile;
        List<String> errorRequests = new ArrayList<>();

        for (SanGiaoDich sanGiaoDich : danhSachMaChungKhoan.keySet()) {
            for (String maChungKhoan : danhSachMaChungKhoan.get(sanGiaoDich)) {
                String url = "http://s.cafef.vn/Lich-su-giao-dich-" + maChungKhoan + "-4.chn";
                int pageNumber = 1;
                String viewState = null;

                System.out.println("Downloading: " + sanGiaoDich + " > " + maChungKhoan);


                while (true) {
                    try {
                        Document document;
                        Map<String, String> params = new HashMap<>();

                        if (pageNumber == 1) {
                            document = fetch(url, null);
                            viewState = document.getElementById("__VIEWSTATE").val();

                            params.put("ctl00$ContentPlaceHolder1$scriptmanager", "ctl00$ContentPlaceHolder1$scriptmanager|ctl00$ContentPlaceHolder1$ctl03$ibtnSearch");
                            params.put("__EVENTTARGET", "");
                            params.put("__EVENTARGUMENT", "");
                            params.put("__VIEWSTATE", viewState);
                            params.put("__VIEWSTATEGENERATOR", "2E2252AF");
                            params.put("ctl00$ContentPlaceHolder1$ctl03$txtKeyword", maChungKhoan);
                            params.put("ctl00$ContentPlaceHolder1$ctl03$dpkTradeDate1$txtDatePicker", "01/01/2007");
                            params.put("ctl00$ContentPlaceHolder1$ctl03$dpkTradeDate2$txtDatePicker", "31/12/2014");
                            params.put("ctl00$ContentPlaceHolder1$ctl03$txtKeyword2", "");
                            params.put("ctl00$ContentPlaceHolder1$ctl03$txtType", "1");
                            params.put("ctl00$UcFooter2$hdIP", "");
                            params.put("__ASYNCPOST", "true");
                        } else {
                            params.put("ctl00$ContentPlaceHolder1$scriptmanager", "ctl00$ContentPlaceHolder1$ctl03$panelAjax|ctl00$ContentPlaceHolder1$ctl03$pager1");
                            params.put("__EVENTTARGET", "ctl00$ContentPlaceHolder1$ctl03$pager1");
                            params.put("__EVENTARGUMENT", pageNumber + "");
                            params.put("__VIEWSTATE", viewState);
                        }

                        document = post(url, params);

                        if (isPageContainsData(document)) {
                            viewState = document.getElementById("__VIEWSTATE").val();

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
//                        errorRequests.add(url + " | " + params);
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

    public static void main(String[] args) {
        downloadGiaoDichCoDongLonVaNoiBo(maChungKhoan, "data/cafef");

    }
}
