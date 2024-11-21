import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XMLToDatabase {
    public static void main(String[] args) throws Exception {
        String xmlData = "<CFIX_DATA>...</CFIX_DATA>"; // Your XML data here
        String dbUrl = "jdbc:your_database_url";
        String dbUser = "your_username";
        String dbPassword = "your_password";

        // Parse XML
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document docum = dBuilder.parse(new InputSource(new StringReader(xmlData)));
        docum.getDocumentElement().normalize();

        // Prepare SQL statement
        String sql = "INSERT INTO gfix_par_bank_auth (BANK_CODE, SYSTEM_CODE, BIZ_FLAG, MSG_TYPE, MSG_SUB_TYPE, USER_REMARK, ALTER_BY, DATA_ENABLE, EDIT_STATUS, OPER_TYPE, CRT_DATETIME, CRT_BRANCH_CODE, CRT_BRANCH_ID, CRT_USER_CODE, CRT_USER_ID, LAST_BRANCH_CODE, LAST_USER_CODE, LAST_DATETIME, LAST_TXN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            NodeList rowNodes = docum.getElementsByTagName("ROW");
            for (int i = 0; i < rowNodes.getLength(); i++) {
                Element row = (Element) rowNodes.item(i);
                String svrGrpInfo = row.getElementsByTagName("SvrGrpInfo").item(0).getTextContent();
                String mmbId = row.getElementsByTagName("MmbId").item(0).getTextContent();
                NodeList bizAuthInfoNodes = row.getElementsByTagName("BizAuthInfo");

                for (int j = 0; j < bizAuthInfoNodes.getLength(); j++) {
                    Element bizAuthInfo = (Element) bizAuthInfoNodes.item(j);
                    NodeList sndBizAuthrtyInfNodes = bizAuthInfo.getElementsByTagName("SndBizAuthrtyInf");
                    NodeList rcvBizAuthrtyInfNodes = bizAuthInfo.getElementsByTagName("RcvBizAuthrtyInf");

                    for (int k = 0; k < sndBizAuthrtyInfNodes.getLength(); k++) {
                        Element sndBizAuthrtyInf = (Element) sndBizAuthrtyInfNodes.item(k);
                        insertRecord(pstmt, svrGrpInfo, mmbId, "SEND", sndBizAuthrtyInf);
                    }

                    for (int k = 0; k < rcvBizAuthrtyInfNodes.getLength(); k++) {
                        Element rcvBizAuthrtyInf = (Element) rcvBizAuthrtyInfNodes.item(k);
                        NodeList prmsnInfNodes = rcvBizAuthrtyInf.getElementsByTagName("PrmsnInf");

                        for (int l = 0; l < prmsnInfNodes.getLength(); l++) {
                            Element prmsnInf = (Element) prmsnInfNodes.item(l);
                            insertRecord(pstmt, svrGrpInfo, mmbId, "RECV", prmsnInf);
                        }
                    }
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            conn.rollback();
        }
    }

    private static void insertRecord(PreparedStatement pstmt, String svrGrpInfo, String mmbId, String bizFlag, Element prmsnInf) throws SQLException {

    }
}
