package io.mine.ft.train.moshi.zerenlian.emp_1.handle.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

/**
 * 授信结果处理
 * @author Mark
 * @since 2019-04-13 10:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreditApplyResultDTO implements Serializable {
    /**
     * 
     */
    private static final long     serialVersionUID = 7629379795257025874L;
    private Long                  userId;
    private String                creditApplyNo;
    private String                productCode;
    private Integer               productCodeVersion;
    private BigDecimal            applyAmount;
    private Date                  applyDate;
    private String                themisNo;
    private String                themisResult;
    private String                auditNo;
    private String                auditResult;
    private Integer               applyStatus;
    private Date                  applyFinishDate;
    private String                themisResultExt;
    private Long                  acctId;
    private String                creditContractNo;
    private BigDecimal            creditAmount;
    private BigDecimal            usedAmount;
    private BigDecimal            frozenAmount;
    private BigDecimal            availableAmount;
    private Date                  effectiveDate;
    private Date                  expireDate;
    private Integer               creditAcctStatus;
    private String                secondMerchantNo;
    private String                creditLevel;
    private String                resultMsg;
    private Integer               raiseType;
    private String                raiseExt;
    /**
     * 前置授信
     */
    private String                OuterCreditApplyNo;
}
