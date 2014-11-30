package info.hieudt.scraper.fpts;

import info.hieudt.scraper.fpts.domain.*;
import info.hieudt.scraper.fpts.util.DataUtil;
import org.hibernate.Session;
import info.hieudt.scraper.fpts.util.HibernateUtil;

import java.util.HashMap;
import java.util.Map;

public class App {
    private static final String BASE_DIR = "data";
    private static final String THONG_KE_INDEX_DIR = BASE_DIR + "/thong-ke-index";
    private static final String DU_LIEU_LICH_DIR = BASE_DIR + "/du-lieu-lich-su";
    private static final String THONG_KE_GIA_DIR = DU_LIEU_LICH_DIR + "/thong-ke-gia";
    private static final String THONG_KE_DAT_LENH_DIR = DU_LIEU_LICH_DIR + "/thong-ke-dat-lenh";
    private static final String GIAO_DICH_KHOP_LENH_DIR = DU_LIEU_LICH_DIR + "/giao-dich-khop-lenh";
    private static final String GIAO_DICH_THOA_THUAN_DIR = DU_LIEU_LICH_DIR + "/giao-dich-thoa-thuan";

    private static final Map<SanGiaoDich, String[]> maChungKhoan = new HashMap<>();

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            ChungKhoan chungKhoan = new ChungKhoan("AAM", SanGiaoDich.HOSE);
            session.save(chungKhoan);

            ThongKeGia thongKeGia = new ThongKeGia(
                    new ThongKeGiaId("27/11/2014", chungKhoan),
                    new Gia(14.8, 15.8, 13.8, 14.7, 14.8, 14.9, 14.7, 0.0),
                    new KhoiLuongGiaoDich(3830, 0, 3830),
                    new GiaTriGiaoDich(57.0, 0.0, 57.0)
            );
            session.save(thongKeGia);

//			Query query = session.createQuery("from ChungKhoan ck where ck.maChungKhoan = :maChungKhoan");
//			query.setParameter("maChungKhoan", "AAM");
//			chungKhoan = (ChungKhoan) query.uniqueResult();
//			System.out.print(chungKhoan);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.shutdown();
        }
    }

    private static void downloadData() {
        maChungKhoan.put(SanGiaoDich.HOSE, new String[]{
                "AAM", "ABT", "ACC", "ACL", "AGF", "AGM", "AGR", "ALP", "ANV", "APC", "ASIAGF", "ASM", "ASP", "ATA", "AVF",
                "BBC", "BCE", "BCI", "BGM", "BHS", "BIC", "BID", "BMC", "BMI", "BMP", "BRC", "BSI", "BT6", "BTP", "BTT", "BVH",
                "C21", "C32", "C47", "CCI", "CCL", "CDC", "CIG", "CII", "CLC", "CLG", "CLL", "CLW", "CMG", "CMT", "CMV", "CMX",
                "CNG", "COM", "CSM", "CTD", "CTG", "CTI", "CYC", "D2D", "DAG", "DCL", "DCT", "DHA", "DHC", "DHG", "DHM", "DIC",
                "DIG", "DLG", "DMC", "DPM", "DPR", "DQC", "DRC", "DRH", "DRL", "DSN", "DTA", "DTL", "DTT", "DVP", "DXG", "DXV",
                "E1VFVN30", "EIB", "ELC", "EMC", "EVE", "FCM", "FCN", "FDC", "FLC", "FMC", "FPT", "GAS", "GDT", "GIL", "GMC",
                "GMD", "GSP", "GTA", "GTN", "GTT", "HAG", "HAI", "HAP", "HAR", "HAS", "HAX", "HBC", "HCM", "HDC", "HDG", "HHS",
                "HLA", "HLG", "HMC", "HOT", "HPG", "HQC", "HRC", "HSG", "HSI", "HT1", "HTI", "HTL", "HTV", "HU1", "HU3", "HVG",
                "HVX", "ICF", "IDI", "IJC", "IMP", "ITA", "ITC", "ITD", "JVC", "KAC", "KBC", "KDC", "KDH", "KHA", "KHP", "KMR",
                "KSA", "KSB", "KSH", "KSS", "KTB", "L10", "LAF", "LBM", "LCG", "LCM", "LGC", "LGL", "LHG", "LIX", "LM8", "LSS",
                "MBB", "MCG", "MCP", "MDG", "MHC", "MPC", "MSN", "MTG", "MWG", "NAV", "NBB", "NHS", "NHW", "NKG", "NLG", "NNC",
                "NSC", "NTL", "NVN", "NVT", "OGC", "OPC", "PAC", "PAN", "PDN", "PDR", "PET", "PGC", "PGD", "PGI", "PHR", "PIT",
                "PJT", "PNC", "PNJ", "POM", "PPC", "PPI", "PTB", "PTC", "PTK", "PTL", "PVD", "PVT", "PXI", "PXL", "PXS", "PXT",
                "QBS", "QCG", "RAL", "RDP", "REE", "RIC", "SAM", "SAV", "SBA", "SBC", "SBT", "SC5", "SCD", "SEC", "SFC", "SFI",
                "SGT", "SHI", "SHP", "SII", "SJD", "SJS", "SKG", "SMA", "SMC", "SPM", "SRC", "SRF", "SSC", "SSI", "ST8", "STB",
                "STG", "STT", "SVC", "SVI", "SVT", "SZL", "TAC", "TBC", "TCL", "TCM", "TCO", "TCR", "TDC", "TDH", "TDW", "THG",
                "TIC", "TIE", "TIX", "TLG", "TLH", "TMP", "TMS", "TMT", "TNA", "TNC", "TNT", "TPC", "TRA", "TRC", "TS4", "TSC",
                "TTF", "TTP", "TV1", "TYA", "UDC", "UIC", "VCB", "VCF", "VFG", "VHC", "VHG", "VIC", "VID", "VIP", "VIS", "VLF",
                "VMD", "VNA", "VNE", "VNG", "VNH", "VNI", "VNL", "VNM", "VNS", "VOS", "VPH", "VPK", "VRC", "VSC", "VSH", "VSI",
                "VST", "VTB", "VTF", "VTO"
        });
        maChungKhoan.put(SanGiaoDich.HNX, new String[]{
                "AAA", "ACB", "ADC", "ALT", "ALV", "AMC", "AME", "AMV", "APG", "API", "APP", "APS", "ARM", "ASA", "B82", "BAM",
                "BBS", "BCC", "BDB", "BED", "BHT", "BII", "BKC", "BLF", "BPC", "BSC", "BST", "BTH", "BTS", "BVG", "BVS", "BXH",
                "C92", "CAN", "CAP", "CCM", "CEO", "CHP", "CID", "CJC", "CKV", "CMC", "CMI", "CMS", "CPC", "CSC", "CT6", "CTA",
                "CTB", "CTC", "CTM", "CTN", "CTS", "CTX", "CVN", "CVT", "CX8", "D11", "DAC", "DAD", "DAE", "DBC", "DBT", "DC2",
                "DC4", "DCS", "DGC", "DHP", "DHT", "DID", "DIH", "DL1", "DLR", "DNC", "DNM", "DNP", "DNY", "DPC", "DST", "DXP",
                "DZM", "EBS", "ECI", "EFI", "EID", "FDT", "FIT", "GLT", "GMX", "HAD", "HAT", "HBE", "HBS", "HCC", "HCT", "HDA",
                "HDO", "HEV", "HGM", "HHC", "HHG", "HJS", "HLC", "HLD", "HLY", "HMH", "HNM", "HOM", "HPC", "HPS", "HST", "HTC",
                "HTP", "HUT", "HVT", "ICG", "IDJ", "IDV", "INC", "INN", "ITQ", "IVS", "KHB", "KHL", "KKC", "KLF", "KLS", "KMT",
                "KSD", "KSK", "KSQ", "KST", "KTS", "KTT", "L14", "L18", "L35", "L43", "L44", "L61", "L62", "LAS", "LBE", "LCD",
                "LCS", "LDP", "LHC", "LIG", "LM3", "LM7", "LO5", "LTC", "LUT", "MAC", "MAS", "MAX", "MCC", "MCF", "MCO", "MDC",
                "MEC", "MHL", "MIM", "MKV", "MNC", "NAG", "NBC", "NBP", "NDF", "NDN", "NDX", "NET", "NFC", "NGC", "NHA", "NHC",
                "NLC", "NPS", "NST", "NTP", "NVB", "OCH", "ONE", "ORS", "PCG", "PCT", "PDC", "PEN", "PFL", "PGS", "PGT", "PHC",
                "PHH", "PID", "PIV", "PJC", "PLC", "PMC", "PMS", "POT", "PPE", "PPG", "PPP", "PPS", "PRC", "PSC", "PSD", "PSI",
                "PTI", "PTM", "PTS", "PV2", "PVB", "PVC", "PVE", "PVG", "PVI", "PVL", "PVR", "PVS", "PVV", "PVX", "PXA", "QHD",
                "QNC", "QST", "QTC", "RCL", "S12", "S55", "S74", "S99", "SAF", "SAP", "SCJ", "SCL", "SCR", "SD1", "SD2", "SD4",
                "SD5", "SD6", "SD7", "SD9", "SDA", "SDC", "SDD", "SDE", "SDG", "SDH", "SDN", "SDP", "SDT", "SDU", "SDY", "SEB",
                "SED", "SFN", "SGC", "SGD", "SGH", "SHA", "SHB", "SHN", "SHS", "SIC", "SJ1", "SJC", "SJE", "SKS", "SLS", "SMT",
                "SPI", "SPP", "SQC", "SRA", "SRB", "SSG", "SSM", "STC", "STP", "SVN", "TAG", "TBX", "TC6", "TCS", "TCT", "TDN",
                "TET", "TH1", "THB", "THS", "THT", "TIG", "TJC", "TKC", "TKU", "TMC", "TMX", "TNG", "TPH", "TPP", "TSB", "TSM",
                "TST", "TTC", "TTZ", "TV2", "TV3", "TV4", "TVC", "TVD", "TXM", "UNI", "V12", "V15", "V21", "VAT", "VBC", "VBH",
                "VC1", "VC2", "VC3", "VC5", "VC6", "VC7", "VC9", "VCC", "VCG", "VCM", "VCR", "VCS", "VDL", "VDS", "VE1", "VE2",
                "VE3", "VE4", "VE8", "VE9", "VFR", "VGP", "VGS", "VHL", "VIE", "VIG", "VIT", "VIX", "VKC", "VLA", "VMC", "VMI",
                "VNC", "VND", "VNF", "VNN", "VNR", "VNT", "VPC", "VTC", "VTH", "VTL", "VTS", "VTV", "VXB", "WCS", "WSS"
        });
        maChungKhoan.put(SanGiaoDich.UPCOM, new String[]{
                "ABI", "ACE", "ADP", "AMD", "ASD", "BHP", "BMJ", "BTC", "BTG", "BTW", "BVN", "BWA", "CAD", "CEC", "CFC", "CI5",
                "CNC", "CT3", "CZC", "D26", "DAP", "DAS", "DBF", "DBM", "DDN", "DGT", "DLC", "DLD", "DNF", "DNL", "DNS", "DPP",
                "DTC", "DTN", "DTV", "DVC", "DVH", "DXL", "FBA", "GDW", "GER", "GGG", "GHC", "GTH", "H11", "HBD", "HCI", "HDM",
                "HFC", "HFX", "HIG", "HPB", "HPP", "HPT", "ICI", "IHK", "IME", "IN4", "JSC", "KBE", "KCE", "KSC", "LCC", "LKW",
                "MDF", "MEF", "MMC", "MTC", "MTH", "MTP", "NBS", "NBW", "ND2", "NDC", "NHN", "NNT", "NOS", "NT2", "NTB", "NTW",
                "PEC", "PJS", "PMT", "POV", "PRO", "PSB", "PSL", "PSP", "PTD", "PTG", "PTH", "PTP", "PTT", "PVA", "PX1", "PXM",
                "QCC", "QPH", "S27", "S33", "S96", "SBS", "SCC", "SCI", "SCO", "SD3", "SDB", "SDI", "SDK", "SDV", "SFT", "SGS",
                "SHV", "SJM", "SMB", "SNC", "SPC", "SPD", "SPH", "SSF", "SSN", "STL", "STS", "STU", "STV", "SWC", "TBD", "TBT",
                "TDS", "TGP", "TIS", "TLT", "TMW", "TNB", "TNM", "TNY", "TTG", "TTR", "TVG", "UDJ", "V11", "VCA", "VCT", "VCX",
                "VDN", "VDT", "VFC", "VHF", "VHH", "VIN", "VIR", "VKD", "VKP", "VNX", "VQC", "VRG", "VSG", "VSP", "VT1", "VTA",
                "VTI", "VTX", "WSB", "WTC", "XPH", "YBC"
        });

        DataUtil.downloadThongKeIndex("http://priceboard.fpts.com.vn/user/stock/thong-ke/", THONG_KE_INDEX_DIR);
        DataUtil.downloadDuLieuLichSu("http://priceboard.fpts.com.vn/user/stock/lich-su/", maChungKhoan, THONG_KE_GIA_DIR);
        DataUtil.downloadDuLieuLichSu("http://priceboard.fpts.com.vn/user/stock/lich-su-lenh/", maChungKhoan, THONG_KE_DAT_LENH_DIR);
        DataUtil.downloadDuLieuLichSu("http://priceboard.fpts.com.vn/user/stock/ndtnn/", maChungKhoan, GIAO_DICH_KHOP_LENH_DIR);
        DataUtil.downloadDuLieuLichSu("http://priceboard.fpts.com.vn/user/stock/ndtnntt/", maChungKhoan, GIAO_DICH_THOA_THUAN_DIR);
    }
}
