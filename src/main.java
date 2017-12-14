import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class main {

	public static void main(String[] args) {

		// For ConvertStrongToDate
		// String s = "1!8830949B!19!MX
		// 3.1!8763826!2975965!0!09/05/16!09/05/16!B!USD!9073.44!35.028!35.115!35.115!MKT_OP!KSM!CJT08153!CONTACT!PROFWD!CORP!CORP!CORP!CORP";
		// ConverStringTodate.convertStringToDate(s);

		// For GetTableFromActuateQuery
		String query = "SELECT FXALL.*, \r\n" + 
				" (RPAD(NVL(TRIM(FXOUT.MAXOFBGL),' '),14,' ')) As M_H_BGL,\r\n" + 
				" (RPAD(NVL(TRIM(FXOUT.MAXOFSGL),' '),14,' ')) As M_H_SGL,\r\n" + 
				" NVL(FXOUT.MAXOFEXTPROD,'   ') As M_H_EXT_PROD,\r\n" + 
				" RPAD(CASE WHEN TRIM(UDF.M_OBJ_TYPE) IS NULL THEN ' ' ELSE UDF.M_OBJ_TYPE END,6,' ') AS M_H_CONT_OBJ,\r\n" + 
				" RPAD(CASE WHEN TRIM(UDF.M_OBJ_DESC) IS NULL THEN ' ' ELSE UDF.M_OBJ_DESC END,400,' ') AS M_H_CONT_DESC,\r\n" + 
				" RPAD(CASE WHEN TRIM(UDF.M_BOT_REFNO1) IS NULL THEN ' ' ELSE UDF.M_BOT_REFNO1 END,40,' ') AS M_H_BOT_REFNO1,\r\n" + 
				" RPAD(CASE WHEN TRIM(UDF.M_BOT_REFNO2) IS NULL THEN ' ' ELSE UDF.M_BOT_REFNO2 END,40,' ') AS M_H_BOT_REFNO2,\r\n" + 
				" RPAD(CASE WHEN TRIM(UDF.M_CANCEL_ID) IS NULL THEN ' ' ELSE UDF.M_CANCEL_ID END,6,' ') AS M_H_CANCEL_ID,\r\n" + 
				" RPAD(CASE WHEN TRIM(UDF.M_UNDL_OWNER) IS NULL THEN ' ' ELSE UDF.M_UNDL_OWNER END,200,' ') AS M_H_UNDL_OWNER\r\n" + 
				" \r\n" + 
				" FROM\r\n" + 
				" (\r\n" + 
				" SELECT \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_REC_IND, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_DAT_GRP, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_DAT_SRC,  \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_NE_REP.M_H_AS_AT,'DDMMYYYY')) As M_H_AS_AT, \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_NE_REP.M_H_TXN_DTE,'DDMMYYYY')) As M_H_TXN_DTE, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_ORG_ID, \r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_NE_REP.M_H_EMP_ID),5,' '),TMB_DWH_FXTXN_NE_REP.M_H_EMP_ID)) As M_H_EMP_ID,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_NE_REP.M_H_ORG_AR),30,' '),TMB_DWH_FXTXN_NE_REP.M_H_ORG_AR)) As M_H_ORG_AR,\r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_COUNID,\r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_PAY_MET, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_FROM_TYP, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_TO_TYP, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_IN_PP, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_OUT_PP, \r\n" + 
				" (RPAD(' ',200,' ')) As OTHER_TXN_PP_DESC,  \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_NE_REP.M_H_SETT_RTE,'99990.0000000')) As M_H_SETT_RTE, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_BCCY,  \r\n" + 
				" (TO_CHAR((CASE WHEN ((CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '999999') \r\n" + 
				"    THEN (TMB_DWH_FXTXN_NE_REP.M_H_SAMT_ORG) \r\n" + 
				"    ELSE \r\n" + 
				"     (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '128003' OR TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '128008')\r\n" + 
				"      THEN NVL(TMB_DWH_FXTXN_NE_REP.M_H_SAMT_UTL,0) \r\n" + 
				"      ELSE (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_SAMT_UTL <> 0)\r\n" + 
				"        THEN NVL(TMB_DWH_FXTXN_NE_REP.M_H_SAMT_UTL,0) \r\n" + 
				"        ELSE  (NVL(TMB_DWH_FXTXN_NE_REP.M_H_SAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0)) \r\n" + 
				"       END)\r\n" + 
				"     END) \r\n" + 
				"      END) = 0) \r\n" + 
				"  THEN 0 \r\n" + 
				"  ELSE\r\n" + 
				"   (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '999999') \r\n" + 
				"    THEN (TMB_DWH_FXTXN_NE_REP.M_H_BAMT_ORG) \r\n" + 
				"    ELSE \r\n" + 
				"     (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '128003' OR TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '128008')\r\n" + 
				"      THEN NVL(TMB_DWH_FXTXN_NE_REP.M_H_BAMT_UTL,0) \r\n" + 
				"      ELSE (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_BAMT_UTL <> 0)\r\n" + 
				"        THEN NVL(TMB_DWH_FXTXN_NE_REP.M_H_BAMT_UTL,0) \r\n" + 
				"        ELSE  (NVL(TMB_DWH_FXTXN_NE_REP.M_H_BAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0)) \r\n" + 
				"       END)\r\n" + 
				"     END) \r\n" + 
				"   END)\r\n" + 
				" END),'9999999999999990.00')) As BUY_AMT, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_SCCY,  \r\n" + 
				" (TO_CHAR((CASE WHEN ((CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '999999') \r\n" + 
				"    THEN (TMB_DWH_FXTXN_NE_REP.M_H_BAMT_ORG) \r\n" + 
				"    ELSE \r\n" + 
				"     (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '128008')\r\n" + 
				"      THEN NVL(TMB_DWH_FXTXN_NE_REP.M_H_BAMT_UTL,0) \r\n" + 
				"      ELSE (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_BAMT_UTL <> 0)\r\n" + 
				"        THEN NVL(TMB_DWH_FXTXN_NE_REP.M_H_BAMT_UTL,0) \r\n" + 
				"        ELSE  (NVL(TMB_DWH_FXTXN_NE_REP.M_H_BAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0)) \r\n" + 
				"       END)\r\n" + 
				"     END) \r\n" + 
				"      END) = 0)\r\n" + 
				"  THEN 0\r\n" + 
				"  ELSE\r\n" + 
				"   (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '999999') \r\n" + 
				"    THEN (TMB_DWH_FXTXN_NE_REP.M_H_SAMT_ORG) \r\n" + 
				"    ELSE \r\n" + 
				"     (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_NE_REP.M_H_TXN_TYPE = '128008')\r\n" + 
				"      THEN NVL(TMB_DWH_FXTXN_NE_REP.M_H_SAMT_UTL,0) \r\n" + 
				"      ELSE (CASE WHEN (TMB_DWH_FXTXN_NE_REP.M_H_SAMT_UTL <> 0)\r\n" + 
				"        THEN NVL(TMB_DWH_FXTXN_NE_REP.M_H_SAMT_UTL,0) \r\n" + 
				"        ELSE  (NVL(TMB_DWH_FXTXN_NE_REP.M_H_SAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0)) \r\n" + 
				"       END)\r\n" + 
				"     END) \r\n" + 
				"   END)\r\n" + 
				" END),'9999999999999990.00')) As SELL_AMT, \r\n" + 
				" (RPAD(TMB_DWH_FXTXN_NE_REP.M_H_BENE_NA,200,' ')) As M_H_BENE_NA,\r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_BENE_CD, \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_BENE_RE,\r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_PROD_CD, \r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_NE_REP.M_H_TYPE_CRD),4,' '),TMB_DWH_FXTXN_NE_REP.M_H_TYPE_CRD)) As M_H_TYPE_CRD,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_NE_REP.M_H_ACC_ID),13,' '),TMB_DWH_FXTXN_NE_REP.M_H_ACC_ID)) As M_H_ACC_ID,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_NE_REP.M_H_SUFFIX),3,' '),TMB_DWH_FXTXN_NE_REP.M_H_SUFFIX)) As M_H_SUFFIX, \r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_NE_REP.M_H_TMB_REF),7,' ')) AS M_H_TMB_REF,\r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_NE_REP.M_H_CREATOR),17,' ')) As M_H_CREATOR,\r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_NE_REP.M_H_EXR_FLAG),1,' ')) As M_H_EXR_FLAG,\r\n" + 
				" 0 AS M_H_NB,\r\n" + 
				" M_H_OFFSET\r\n" + 
				" \r\n" + 
				" FROM \r\n" + 
				" TMB_DWH_FXTXN_NE_REP, TMB_DWH_FXUTL_YS_REP\r\n" + 
				" \r\n" + 
				" WHERE \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_REF_DATA = :MxDataSetKey AND\r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_REF_DATA = TMB_DWH_FXUTL_YS_REP.M_REF_DATA(+) AND \r\n" + 
				" TMB_DWH_FXTXN_NE_REP.M_H_ORG_AR = TMB_DWH_FXUTL_YS_REP.M_H_ORG_AR(+)\r\n" + 
				" \r\n" + 
				" UNION\r\n" + 
				" \r\n" + 
				" SELECT \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_REC_IND, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_DAT_GRP, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_DAT_SRC,  \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_V1_REP.M_H_AS_AT,'DDMMYYYY')) As M_H_AS_AT, \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_V1_REP.M_H_TXN_DTE,'DDMMYYYY')) As M_H_TXN_DTE, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_ORG_ID, \r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V1_REP.M_H_EMP_ID),5,' '),TMB_DWH_FXTXN_V1_REP.M_H_EMP_ID)) As M_H_EMP_ID,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V1_REP.M_H_ORG_AR),30,' '),TMB_DWH_FXTXN_V1_REP.M_H_ORG_AR)) As M_H_ORG_AR,\r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_COUNID, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_PAY_MET, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_FROM_TYP, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_TO_TYP, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_IN_PP, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_OUT_PP,  \r\n" + 
				" (RPAD(' ',200,' ')) As OTHER_TXN_PP_DESC,  \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_V1_REP.M_H_SETT_RTE,'99990.0000000')) As M_H_SETT_RTE, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_BCCY,  \r\n" + 
				" (TO_CHAR(CASE WHEN (CASE WHEN (TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128008')\r\n" + 
				"                 THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL,0) \r\n" + 
				"                 ELSE (CASE WHEN (TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL <> 0)\r\n" + 
				"              THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL,0) \r\n" + 
				"              ELSE  (NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0)) \r\n" + 
				"              END)\r\n" + 
				"                 END) = 0\r\n" + 
				"          THEN 0 \r\n" + 
				"          ELSE (CASE WHEN (TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128008')\r\n" + 
				"      THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL,0) \r\n" + 
				"      ELSE (CASE WHEN (TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL <> 0)\r\n" + 
				"                  THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL,0) \r\n" + 
				"                  ELSE  (NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0)) \r\n" + 
				"                  END)\r\n" + 
				"      END) \r\n" + 
				"                         END,'9999999999999990.00')) As BUY_AMT, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_SCCY,  \r\n" + 
				" (TO_CHAR(CASE WHEN (CASE WHEN (TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128008')\r\n" + 
				"                 THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL,0) \r\n" + 
				"                 ELSE (CASE WHEN (TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL <> 0)\r\n" + 
				"              THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL,0) \r\n" + 
				"              ELSE  (NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0)) \r\n" + 
				"              END)\r\n" + 
				"                 END) = 0\r\n" + 
				"        THEN 0\r\n" + 
				"        ELSE (CASE WHEN (TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128008')\r\n" + 
				"     THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL,0) \r\n" + 
				"       ELSE (CASE WHEN (TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL <> 0)\r\n" + 
				"                 THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL,0) \r\n" + 
				"                 ELSE  (NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0)) \r\n" + 
				"                 END)\r\n" + 
				"    END)\r\n" + 
				"                       END,'9999999999999990.00')) As SELL_AMT, \r\n" + 
				" (RPAD(TMB_DWH_FXTXN_V1_REP.M_H_BENE_NA,200,' ')) As M_H_BENE_NA,\r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_BENE_CD, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_BENE_RE, \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_PROD_CD, \r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V1_REP.M_H_TYPE_CRD),4,' '),TMB_DWH_FXTXN_V1_REP.M_H_TYPE_CRD)) As M_H_TYPE_CRD,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V1_REP.M_H_ACC_ID),13,' '),TMB_DWH_FXTXN_V1_REP.M_H_ACC_ID)) As M_H_ACC_ID,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V1_REP.M_H_SUFFIX),3,' '),TMB_DWH_FXTXN_V1_REP.M_H_SUFFIX)) As M_H_SUFFIX, \r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_V1_REP.M_H_TMB_REF),7,' ')) As M_H_TMB_REF,\r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_V1_REP.M_H_CREATOR),17,' ')) As M_H_CREATOR,\r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_V1_REP.M_H_EXR_FLAG),1,' ')) As M_H_EXR_FLAG,\r\n" + 
				" 0 AS M_H_NB,\r\n" + 
				" M_H_OFFSET\r\n" + 
				" \r\n" + 
				" FROM \r\n" + 
				" TMB_DWH_FXTXN_V1_REP, TMB_DWH_FXUTL_YS_REP\r\n" + 
				" \r\n" + 
				" WHERE \r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_REF_DATA = :MxDataSetKey AND\r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_REF_DATA = TMB_DWH_FXUTL_YS_REP.M_REF_DATA(+) AND\r\n" + 
				" TMB_DWH_FXTXN_V1_REP.M_H_ORG_AR = TMB_DWH_FXUTL_YS_REP.M_H_ORG_AR(+) AND\r\n" + 
				" (CASE WHEN (CASE WHEN TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128008'\r\n" + 
				"                      THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL,0) \r\n" + 
				"           ELSE (CASE WHEN TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL <> 0\r\n" + 
				"        THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL,0) \r\n" + 
				"        ELSE  NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0)\r\n" + 
				"        END)\r\n" + 
				"            END) = 0\r\n" + 
				" THEN 0 \r\n" + 
				" ELSE (CASE WHEN (TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128008')\r\n" + 
				"             THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL,0) \r\n" + 
				"             ELSE (CASE WHEN TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL <> 0\r\n" + 
				"           THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL,0) \r\n" + 
				"          ELSE  NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0)\r\n" + 
				"           END)\r\n" + 
				"             END) \r\n" + 
				" END) <> 0 AND\r\n" + 
				" (CASE WHEN (CASE WHEN TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128008'\r\n" + 
				"            THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL,0) \r\n" + 
				"           ELSE (CASE WHEN TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL <> 0\r\n" + 
				"         THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_UTL,0) \r\n" + 
				"        ELSE  NVL(TMB_DWH_FXTXN_V1_REP.M_H_BAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0)\r\n" + 
				"        END)\r\n" + 
				"                           END) = 0\r\n" + 
				" THEN 0\r\n" + 
				" ELSE (CASE WHEN TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V1_REP.M_H_TXN_TYPE = '128008'\r\n" + 
				"            THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL,0) \r\n" + 
				"            ELSE (CASE WHEN TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL <> 0\r\n" + 
				"        THEN NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_UTL,0) \r\n" + 
				"        ELSE  NVL(TMB_DWH_FXTXN_V1_REP.M_H_SAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0)\r\n" + 
				"        END)\r\n" + 
				"             END) \r\n" + 
				" END) <> 0\r\n" + 
				" \r\n" + 
				" UNION\r\n" + 
				" \r\n" + 
				" SELECT \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_REC_IND,\r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_DAT_GRP, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_DAT_SRC,  \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_V2_REP.M_H_AS_AT,'DDMMYYYY')) As M_H_AS_AT, \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_V2_REP.M_H_TXN_DTE,'DDMMYYYY')) As M_H_TXN_DTE, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_ORG_ID, \r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V2_REP.M_H_EMP_ID),5,' '),TMB_DWH_FXTXN_V2_REP.M_H_EMP_ID)) As M_H_EMP_ID,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V2_REP.M_H_ORG_AR),30,' '),TMB_DWH_FXTXN_V2_REP.M_H_ORG_AR)) As M_H_ORG_AR,\r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_COUNID, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_PAY_MET, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_FROM_TYP, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_TO_TYP, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_IN_PP, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_OUT_PP,  \r\n" + 
				" (RPAD(' ',200,' ')) As OTHER_TXN_PP_DESC,  \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_V2_REP.M_H_SETT_RTE,'99990.0000000') ) As M_H_SETT_RTE, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_BCCY,  \r\n" + 
				" (TO_CHAR(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128003' OR TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128008' OR (TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128002' AND (TMB_DWH_FXTXN_V2_REP.M_H_CRTOP1='PET' OR TMB_DWH_FXTXN_V2_REP.M_H_CRTOP1='ET'))\r\n" + 
				"                      THEN NVL(TMB_DWH_FXTXN_V2_REP.M_H_BAMT_UTL,0) \r\n" + 
				"       ELSE NVL(TMB_DWH_FXTXN_V2_REP.M_H_BAMT_ORG,0) - \r\n" + 
				"                                NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0) - \r\n" + 
				"                                NVL(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY <> 'THB' THEN FXEXT.BAMT ELSE TMB_DWH_FXTXN_V2_REP.M_H_SETT_RTE * FXEXT.SAMT END,0) - \r\n" + 
				"                                NVL(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY <> 'THB' THEN (CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY=FXROL.M_BRW_NOMU1 THEN FXROL.M_BRW_NOM1 ELSE FXROL.M_BRW_NOM2 END) ELSE TMB_DWH_FXTXN_V2_REP.M_H_SETT_RTE * (CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY=FXROL.M_BRW_NOMU1 THEN FXROL.M_BRW_NOM1 ELSE FXROL.M_BRW_NOM2 END) END,0) -\r\n" + 
				"                                NVL((CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY=FXOFF.M_BRW_NOMU1 THEN FXOFF.M_BRW_NOM1 ELSE FXOFF.M_BRW_NOM2 END),0)\r\n" + 
				"       END,'9999999999999990.00')) As BUY_AMT, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_SCCY,  \r\n" + 
				" (TO_CHAR(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128003' OR TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128008' OR (TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128002' AND (TMB_DWH_FXTXN_V2_REP.M_H_CRTOP1='PET' OR TMB_DWH_FXTXN_V2_REP.M_H_CRTOP1='ET'))\r\n" + 
				"       THEN NVL(TMB_DWH_FXTXN_V2_REP.M_H_SAMT_UTL,0) \r\n" + 
				"       ELSE NVL(TMB_DWH_FXTXN_V2_REP.M_H_SAMT_ORG,0) - \r\n" + 
				"                                NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0) - \r\n" + 
				"                                NVL(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY <> 'THB' THEN FXEXT.SAMT ELSE TMB_DWH_FXTXN_V2_REP.M_H_SETT_RTE * FXEXT.BAMT END,0) -\r\n" + 
				"                                NVL(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY <> 'THB' THEN (CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY=FXROL.M_BRW_NOMU1 THEN FXROL.M_BRW_NOM1 ELSE FXROL.M_BRW_NOM2 END) ELSE TMB_DWH_FXTXN_V2_REP.M_H_SETT_RTE * (CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY=FXROL.M_BRW_NOMU1 THEN FXROL.M_BRW_NOM1 ELSE FXROL.M_BRW_NOM2 END) END,0) -\r\n" + 
				"                                NVL((CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY=FXOFF.M_BRW_NOMU1 THEN FXOFF.M_BRW_NOM1 ELSE FXOFF.M_BRW_NOM2 END),0)\r\n" + 
				"       END,'9999999999999990.00')) As SELL_AMT, \r\n" + 
				" (RPAD(TMB_DWH_FXTXN_V2_REP.M_H_BENE_NA,200,' ')) As M_H_BENE_NA,\r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_BENE_CD, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_BENE_RE, \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_PROD_CD, \r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V2_REP.M_H_TYPE_CRD),4,' '),TMB_DWH_FXTXN_V2_REP.M_H_TYPE_CRD)) As M_H_TYPE_CRD,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V2_REP.M_H_ACC_ID),13,' '),TMB_DWH_FXTXN_V2_REP.M_H_ACC_ID)) As M_H_ACC_ID,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V2_REP.M_H_SUFFIX),3,' '),TMB_DWH_FXTXN_V2_REP.M_H_SUFFIX)) As M_H_SUFFIX, \r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_V2_REP.M_H_TMB_REF),7,' ')) As M_H_TMB_REF,\r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_V2_REP.M_H_CREATOR),17,' ')) As M_H_CREATOR,\r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_V2_REP.M_H_EXR_FLAG),1,' ')) As M_H_EXR_FLAG,\r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_NB,\r\n" + 
				" M_H_OFFSET\r\n" + 
				" \r\n" + 
				" FROM \r\n" + 
				" TMB_DWH_FXTXN_V2_REP, TMB_DWH_FXUTL_YS_REP,\r\n" + 
				" (SELECT DISTINCT B.M_H_NB, \r\n" + 
				"                (CASE WHEN B.M_H_BCCY=A.M_BRW_NOMU1 THEN A.M_BRW_NOM1 ELSE A.M_BRW_NOM2 END) AS BAMT, \r\n" + 
				"                (CASE WHEN B.M_H_SCCY=A.M_BRW_NOMU1 THEN A.M_BRW_NOM1 ELSE A.M_BRW_NOM2 END) AS SAMT \r\n" + 
				"  FROM  (Select A1.M_UNDDEAL_NB, A2.M_BRW_NOMU1, A2.M_BRW_NOMU2, A2.M_BRW_NOM1, A2.M_BRW_NOM2, A2.M_MOP_LAST, A2.M_OPT_MOPLSD, A2.M_OPT_FLWFST  from FXMM_MOP_DBF A1, TRN_HDR_DBF A2 where A1.M_TRADE4_NB=A2.M_NB and (A1.M_TYPE='EX' or A1.M_TYPE='PEX') and A2.M_TRN_TYPE='XSW' and A2.M_VAL_STATUS='CONF' and (A2.M_SINTERNAL='N' or A2.M_BINTERNAL='N') and substr(A2.M_BPFOLIO,1,6)<>'BKTMSA' and substr(A2.M_SPFOLIO,1,6)<>'BKTMSA') A, \r\n" + 
				"               (Select * from TMB_DWH_FXTXN_V2_REP where M_REF_DATA=:MxDataSetKey and M_H_SALES='N') B\r\n" + 
				"  WHERE A.M_UNDDEAL_NB=B.M_H_NB \r\n" + 
				"       AND A.M_OPT_FLWFST <= B.M_H_AS_AT\r\n" + 
				"       AND (CASE WHEN A.M_MOP_LAST <> 6 and A.M_MOP_LAST <> 7 THEN 'T' ELSE (CASE WHEN A.M_OPT_MOPLSD <= B.M_H_AS_AT THEN 'F' ELSE 'T' END) END)='T' \r\n" + 
				" ) FXEXT,\r\n" + 
				" (SELECT distinct A1.M_ROLLOVERNO, A2.M_OPT_FLWFST, A2.M_MOP_LAST, A2.M_OPT_MOPLSD, A2.M_BRW_NOMU1, A2.M_BRW_NOMU2, A2.M_BRW_NOM1, A2.M_BRW_NOM2\r\n" + 
				"  FROM (Select M_ROLLOVERNO, M_NB from TABLE#DATA#DEALCURR_DBF where M_ROLLOVERNO <> 0) A1, TRN_HDR_DBF A2\r\n" + 
				"  WHERE A1.M_NB=A2.M_NB \r\n" + 
				"        AND A2.M_VAL_STATUS='CONF' \r\n" + 
				"        AND (A2.M_SINTERNAL='N' or A2.M_BINTERNAL='N')\r\n" + 
				"        AND substr(A2.M_BPFOLIO,1,6)<>'BKTMSA' and substr(A2.M_SPFOLIO,1,6)<>'BKTMSA'\r\n" + 
				"        AND A2.M_OPT_FLWFST <= (Select MIN(M_H_AS_AT) AS asat from TMB_DWH_FXTXN_V2_REP where M_REF_DATA=:MxDataSetKey)\r\n" + 
				"        AND (CASE WHEN A2.M_MOP_LAST <> 6 and A2.M_MOP_LAST <> 7 THEN 'T' ELSE (CASE WHEN A2.M_OPT_MOPLSD <= (Select MIN(M_H_AS_AT) AS asat from TMB_DWH_FXTXN_V2_REP where M_REF_DATA=:MxDataSetKey) THEN 'F' ELSE 'T' END) END)='T'\r\n" + 
				" ) FXROL,\r\n" + 
				" (SELECT distinct A1.M_OFFSET_NO, A2.M_OPT_FLWFST, A2.M_MOP_LAST, A2.M_OPT_MOPLSD, A2.M_BRW_NOMU1, A2.M_BRW_NOMU2, A2.M_BRW_NOM1, A2.M_BRW_NOM2\r\n" + 
				" FROM (Select M_OFFSET_NO, M_NB from TABLE#DATA#DEALCURR_DBF where M_OFFSET_NO<>0) A1, TRN_HDR_DBF A2 \r\n" + 
				" WHERE A1.M_NB=A2.M_NB \r\n" + 
				"       AND A2.M_VAL_STATUS='CONF'\r\n" + 
				"       AND (A2.M_SINTERNAL='N' or A2.M_BINTERNAL='N')\r\n" + 
				"       AND substr(A2.M_BPFOLIO,1,6)<>'BKTMSA' and substr(A2.M_SPFOLIO,1,6)<>'BKTMSA'\r\n" + 
				"       AND A2.M_OPT_FLWFST <= (Select MIN(M_H_AS_AT) AS asat from TMB_DWH_FXTXN_V2_REP where M_REF_DATA=:MxDataSetKey) \r\n" + 
				"       AND (CASE WHEN A2.M_MOP_LAST <> 6 and A2.M_MOP_LAST <> 7 THEN 'T' ELSE (CASE WHEN A2.M_OPT_MOPLSD <= (Select MIN(M_H_AS_AT) AS asat from TMB_DWH_FXTXN_V2_REP where M_REF_DATA=:MxDataSetKey) THEN 'F' ELSE 'T' END) END)='T'\r\n" + 
				" ) FXOFF\r\n" + 
				" \r\n" + 
				" WHERE \r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_REF_DATA = :MxDataSetKey AND\r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_REF_DATA = TMB_DWH_FXUTL_YS_REP.M_REF_DATA(+) AND\r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_ORG_AR = TMB_DWH_FXUTL_YS_REP.M_H_ORG_AR(+) AND\r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_NB = FXEXT.M_H_NB(+) AND\r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_NB = FXROL.M_ROLLOVERNO(+) AND\r\n" + 
				" TMB_DWH_FXTXN_V2_REP.M_H_NB = FXOFF.M_OFFSET_NO(+) AND\r\n" + 
				" (TO_CHAR(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128003' OR TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128008' OR (TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128002' AND (TMB_DWH_FXTXN_V2_REP.M_H_CRTOP1='PET' OR TMB_DWH_FXTXN_V2_REP.M_H_CRTOP1='ET'))\r\n" + 
				"                      THEN NVL(TMB_DWH_FXTXN_V2_REP.M_H_BAMT_UTL,0) \r\n" + 
				"       ELSE NVL(TMB_DWH_FXTXN_V2_REP.M_H_BAMT_ORG,0) - \r\n" + 
				"                                NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0) - \r\n" + 
				"                                NVL(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY <> 'THB' THEN FXEXT.BAMT ELSE TMB_DWH_FXTXN_V2_REP.M_H_SETT_RTE * FXEXT.SAMT END,0) -\r\n" + 
				"                                NVL(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY <> 'THB' THEN (CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY=FXROL.M_BRW_NOMU1 THEN FXROL.M_BRW_NOM1 ELSE FXROL.M_BRW_NOM2 END) ELSE TMB_DWH_FXTXN_V2_REP.M_H_SETT_RTE * (CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY=FXROL.M_BRW_NOMU1 THEN FXROL.M_BRW_NOM1 ELSE FXROL.M_BRW_NOM2 END) END,0) -\r\n" + 
				"                                NVL((CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY=FXOFF.M_BRW_NOMU1 THEN FXOFF.M_BRW_NOM1 ELSE FXOFF.M_BRW_NOM2 END),0)\r\n" + 
				"       END,'9999999999999990.00')) > 0 AND\r\n" + 
				" (TO_CHAR(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128003' OR TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128008' OR (TMB_DWH_FXTXN_V2_REP.M_H_TXN_TYPE = '128002' AND (TMB_DWH_FXTXN_V2_REP.M_H_CRTOP1='PET' OR TMB_DWH_FXTXN_V2_REP.M_H_CRTOP1='ET'))\r\n" + 
				"       THEN NVL(TMB_DWH_FXTXN_V2_REP.M_H_SAMT_UTL,0) \r\n" + 
				"       ELSE NVL(TMB_DWH_FXTXN_V2_REP.M_H_SAMT_ORG,0) - \r\n" + 
				"                                NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0) - \r\n" + 
				"                                NVL(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY <> 'THB' THEN FXEXT.SAMT ELSE TMB_DWH_FXTXN_V2_REP.M_H_SETT_RTE * FXEXT.BAMT END,0) -\r\n" + 
				"                                NVL(CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY <> 'THB' THEN (CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY=FXROL.M_BRW_NOMU1 THEN FXROL.M_BRW_NOM1 ELSE FXROL.M_BRW_NOM2 END) ELSE TMB_DWH_FXTXN_V2_REP.M_H_SETT_RTE * (CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_BCCY=FXROL.M_BRW_NOMU1 THEN FXROL.M_BRW_NOM1 ELSE FXROL.M_BRW_NOM2 END) END,0) -\r\n" + 
				"                                NVL((CASE WHEN TMB_DWH_FXTXN_V2_REP.M_H_SCCY=FXOFF.M_BRW_NOMU1 THEN FXOFF.M_BRW_NOM1 ELSE FXOFF.M_BRW_NOM2 END),0)\r\n" + 
				"       END,'9999999999999990.00')) > 0\r\n" + 
				" \r\n" + 
				" UNION\r\n" + 
				" \r\n" + 
				" SELECT \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_REC_IND,\r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_DAT_GRP, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_DAT_SRC,  \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_V3_REP.M_H_AS_AT,'DDMMYYYY')) As M_H_AS_AT, \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_V3_REP.M_H_TXN_DTE,'DDMMYYYY')) As M_H_TXN_DTE, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_ORG_ID, \r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V3_REP.M_H_EMP_ID),5,' '),TMB_DWH_FXTXN_V3_REP.M_H_EMP_ID)) As M_H_EMP_ID,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V3_REP.M_H_ORG_AR),30,' '),TMB_DWH_FXTXN_V3_REP.M_H_ORG_AR)) As M_H_ORG_AR,\r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_TXN_TYPE, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_COUNID, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_PAY_MET, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_FROM_TYP, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_TO_TYP, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_IN_PP, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_OUT_PP,  \r\n" + 
				" (RPAD(' ',200,' ')) As OTHER_TXN_PP_DESC,  \r\n" + 
				" (TO_CHAR(TMB_DWH_FXTXN_V3_REP.M_H_SETT_RTE,'99990.0000000') ) As M_H_SETT_RTE, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_BCCY,  \r\n" + 
				" (TO_CHAR((CASE WHEN (CASE WHEN TMB_DWH_FXTXN_V3_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V3_REP.M_H_TXN_TYPE = '128008'\r\n" + 
				"                 THEN NVL(TMB_DWH_FXTXN_V3_REP.M_H_SAMT_UTL,0) \r\n" + 
				"                 ELSE (CASE WHEN TMB_DWH_FXTXN_V3_REP.M_H_SAMT_UTL <> 0\r\n" + 
				"              THEN NVL(TMB_DWH_FXTXN_V3_REP.M_H_SAMT_UTL,0) \r\n" + 
				"              ELSE  (NVL(TMB_DWH_FXTXN_V3_REP.M_H_SAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0)) \r\n" + 
				"                                                           END)\r\n" + 
				"                 END) = 0\r\n" + 
				"          THEN 0 \r\n" + 
				"         ELSE (CASE WHEN TMB_DWH_FXTXN_V3_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V3_REP.M_H_TXN_TYPE = '128008'\r\n" + 
				"      THEN NVL(TMB_DWH_FXTXN_V3_REP.M_H_BAMT_UTL,0) \r\n" + 
				"      ELSE (CASE WHEN (TMB_DWH_FXTXN_V3_REP.M_H_BAMT_UTL <> 0)\r\n" + 
				"                  THEN NVL(TMB_DWH_FXTXN_V3_REP.M_H_BAMT_UTL,0) \r\n" + 
				"    ELSE  (NVL(TMB_DWH_FXTXN_V3_REP.M_H_BAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0)) \r\n" + 
				"    END)\r\n" + 
				"      END) \r\n" + 
				"                        END),'9999999999999990.00')) As BUY_AMT, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_SCCY,  \r\n" + 
				" (TO_CHAR((CASE WHEN (CASE WHEN TMB_DWH_FXTXN_V3_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V3_REP.M_H_TXN_TYPE = '128008'\r\n" + 
				"                 THEN NVL(TMB_DWH_FXTXN_V3_REP.M_H_BAMT_UTL,0) \r\n" + 
				"                 ELSE (CASE WHEN TMB_DWH_FXTXN_V3_REP.M_H_BAMT_UTL <> 0\r\n" + 
				"              THEN NVL(TMB_DWH_FXTXN_V3_REP.M_H_BAMT_UTL,0) \r\n" + 
				"              ELSE  (NVL(TMB_DWH_FXTXN_V3_REP.M_H_BAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_BAMT,0)) \r\n" + 
				"              END)\r\n" + 
				"                 END) = 0\r\n" + 
				"         THEN 0\r\n" + 
				"         ELSE (CASE WHEN TMB_DWH_FXTXN_V3_REP.M_H_TXN_TYPE = '128006' OR TMB_DWH_FXTXN_V3_REP.M_H_TXN_TYPE = '128008'\r\n" + 
				"     THEN NVL(TMB_DWH_FXTXN_V3_REP.M_H_SAMT_UTL,0) \r\n" + 
				"     ELSE (CASE WHEN (TMB_DWH_FXTXN_V3_REP.M_H_SAMT_UTL <> 0)\r\n" + 
				"                THEN NVL(TMB_DWH_FXTXN_V3_REP.M_H_SAMT_UTL,0) \r\n" + 
				"                ELSE  (NVL(TMB_DWH_FXTXN_V3_REP.M_H_SAMT_ORG,0) - NVL(TMB_DWH_FXUTL_YS_REP.M_H_SAMT,0)) \r\n" + 
				"                END)\r\n" + 
				"    END)\r\n" + 
				"                        END),'9999999999999990.00')) As SELL_AMT, \r\n" + 
				" (RPAD(TMB_DWH_FXTXN_V3_REP.M_H_BENE_NA,200,' ')) As M_H_BENE_NA,\r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_BENE_CD, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_BENE_RE, \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_PROD_CD, \r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V3_REP.M_H_TYPE_CRD),4,' '),TMB_DWH_FXTXN_V3_REP.M_H_TYPE_CRD)) As M_H_TYPE_CRD,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V3_REP.M_H_ACC_ID),13,' '),TMB_DWH_FXTXN_V3_REP.M_H_ACC_ID)) As M_H_ACC_ID,\r\n" + 
				" (NVL(RPAD(TRIM(TMB_DWH_FXTXN_V3_REP.M_H_SUFFIX),3,' '),TMB_DWH_FXTXN_V3_REP.M_H_SUFFIX)) As M_H_SUFFIX, \r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_V3_REP.M_H_TMB_REF),7,' ')) As M_H_TMB_REF,\r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_V3_REP.M_H_CREATOR),17,' ')) As M_H_CREATOR,\r\n" + 
				" (RPAD(TRIM(TMB_DWH_FXTXN_V3_REP.M_H_EXR_FLAG),1,' ')) As M_H_EXR_FLAG,\r\n" + 
				" 0 AS M_H_NB,\r\n" + 
				" M_H_OFFSET\r\n" + 
				" \r\n" + 
				" FROM \r\n" + 
				" TMB_DWH_FXTXN_V3_REP, TMB_DWH_FXUTL_YS_REP\r\n" + 
				" \r\n" + 
				" WHERE \r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_REF_DATA = :MxDataSetKey AND\r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_REF_DATA = TMB_DWH_FXUTL_YS_REP.M_REF_DATA(+) AND\r\n" + 
				" TMB_DWH_FXTXN_V3_REP.M_H_ORG_AR = TMB_DWH_FXUTL_YS_REP.M_H_ORG_AR(+)\r\n" + 
				" ) FXALL,\r\n" + 
				" (SELECT M_H_ORG_NUM, MAX(M_H_SGL) AS MAXOFSGL, MAX(M_H_BGL) AS MAXOFBGL, MAX(M_H_EXT_PROD) AS MAXOFEXTPROD FROM TMB_DWH_FXOUT_REP GROUP BY M_H_ORG_NUM) FXOUT,\r\n" + 
				" (SELECT * FROM TABLE#DATA#DEALCURR_DBF) UDF\r\n" + 
				" \r\n" + 
				" WHERE TRIM(FXALL.M_H_ORG_AR) = TRIM(FXOUT.M_H_ORG_NUM)\r\n" + 
				" AND (CASE WHEN FXALL.M_H_OFFSET<>0 THEN TO_NUMBER(M_H_TMB_REF) ELSE TO_NUMBER(CASE WHEN INSTR(FXALL.M_H_ORG_AR,'_') = 0 THEN FXALL.M_H_ORG_AR ELSE SUBSTR(FXALL.M_H_ORG_AR,1,INSTR(FXALL.M_H_ORG_AR,'_')-1) END) END) = UDF.M_NB (+)";

		HashSet<String> listMatches = new HashSet<>();
		HashSet<String> listAll = new HashSet<>();
		
		listMatches = GetTableFromActuateQuery.getTableFromQuery(query, TABLETYPE.DBF);
		listAll = GetTableFromActuateQuery.getAllTable(query);
		
		for (String s : listAll) {
			System.out.println(s);
		}
		
		System.out.println(GetTableFromActuateQuery.hashListToString(listAll));

	}

}
