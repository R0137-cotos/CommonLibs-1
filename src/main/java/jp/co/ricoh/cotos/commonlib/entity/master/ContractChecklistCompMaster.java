package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 契約チェックリスト構成マスタを表すEntity
 */
@Entity
@Data
@Table(name = "contract_checklist_comp_master")
public class ContractChecklistCompMaster {

	public enum TargetContractType {

		共通, 新規, プラン変更, 情報変更;

		@JsonValue
		public String toValue() {
			return this.name();
		}

		@JsonCreator
		public static Enum<TargetContractType> fromValue(String name) {
			return Arrays.stream(values()).filter(v -> v.name() == name).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(name)));
		}
	}

	/**
	 * 商品
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@ApiModelProperty(value = "商品マスタ", required = true, position = 2)
	private ProductMaster productMaster;

	/**
	 * 対象契約種別
	 */
	@ApiModelProperty(value = "対象契約種別<br /> "//
			+ "共通/新規/プラン変更/情報変更", required = true, position = 3)
	private TargetContractType targetContractType;

	/**
	 * 対象ライフサイクル状態
	 */
	@ApiModelProperty(value = "対象ライフサイクル状態<br /> "//
			+ "作成中/キャンセル手続き中/解約手続き中", required = true, position = 4, allowableValues = "range[0,9999999999999999999]")
	private long targetLifecycleStatus;

	/**
	 * 汎用チェック事項マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "gp_check_matter_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "汎用チェック事項マスタ", required = true, position = 5)
	private GpCheckMatterMaster gpCheckMatterMaster;

	/**
	 * 表示順
	 */
	@ApiModelProperty(value = "表示順", required = true, position = 6, allowableValues = "range[0,999]")
	private int displayOrder;

}
