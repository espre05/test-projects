/**
 * This class is generated by jOOQ
 */
package gen.db.cora.tables.records;


import gen.db.cora.tables.Specimens;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.0"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SpecimensRecord extends UpdatableRecordImpl<SpecimensRecord> {

	private static final long serialVersionUID = 1200788952;

	/**
	 * Setter for <code>cora.specimens.id</code>.
	 */
	public void setId(UUID value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>cora.specimens.id</code>.
	 */
	public UUID getId() {
		return (UUID) getValue(0);
	}

	/**
	 * Setter for <code>cora.specimens.order_id</code>.
	 */
	public void setOrderId(UUID value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>cora.specimens.order_id</code>.
	 */
	public UUID getOrderId() {
		return (UUID) getValue(1);
	}

	/**
	 * Setter for <code>cora.specimens.sample_type</code>.
	 */
	public void setSampleType(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>cora.specimens.sample_type</code>.
	 */
	public String getSampleType() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>cora.specimens.arrival_date</code>.
	 */
	public void setArrivalDate(Timestamp value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>cora.specimens.arrival_date</code>.
	 */
	public Timestamp getArrivalDate() {
		return (Timestamp) getValue(3);
	}

	/**
	 * Setter for <code>cora.specimens.reconciliation_date</code>.
	 */
	public void setReconciliationDate(Timestamp value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>cora.specimens.reconciliation_date</code>.
	 */
	public Timestamp getReconciliationDate() {
		return (Timestamp) getValue(4);
	}

	/**
	 * Setter for <code>cora.specimens.collection_date</code>.
	 */
	public void setCollectionDate(Timestamp value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>cora.specimens.collection_date</code>.
	 */
	public Timestamp getCollectionDate() {
		return (Timestamp) getValue(5);
	}

	/**
	 * Setter for <code>cora.specimens.retrieval_date</code>.
	 */
	public void setRetrievalDate(Timestamp value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>cora.specimens.retrieval_date</code>.
	 */
	public Timestamp getRetrievalDate() {
		return (Timestamp) getValue(6);
	}

	/**
	 * Setter for <code>cora.specimens.source_type</code>.
	 */
	public void setSourceType(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>cora.specimens.source_type</code>.
	 */
	public String getSourceType() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>cora.specimens.rationale</code>.
	 */
	public void setRationale(String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>cora.specimens.rationale</code>.
	 */
	public String getRationale() {
		return (String) getValue(8);
	}

	/**
	 * Setter for <code>cora.specimens.anticoagulant</code>.
	 */
	public void setAnticoagulant(String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>cora.specimens.anticoagulant</code>.
	 */
	public String getAnticoagulant() {
		return (String) getValue(9);
	}

	/**
	 * Setter for <code>cora.specimens.quantity_received</code>.
	 */
	public void setQuantityReceived(Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>cora.specimens.quantity_received</code>.
	 */
	public Integer getQuantityReceived() {
		return (Integer) getValue(10);
	}

	/**
	 * Setter for <code>cora.specimens.gdna_concentration</code>.
	 */
	public void setGdnaConcentration(Double value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>cora.specimens.gdna_concentration</code>.
	 */
	public Double getGdnaConcentration() {
		return (Double) getValue(11);
	}

	/**
	 * Setter for <code>cora.specimens.cell_count</code>.
	 */
	public void setCellCount(Double value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>cora.specimens.cell_count</code>.
	 */
	public Double getCellCount() {
		return (Double) getValue(12);
	}

	/**
	 * Setter for <code>cora.specimens.concentration</code>.
	 */
	public void setConcentration(Double value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>cora.specimens.concentration</code>.
	 */
	public Double getConcentration() {
		return (Double) getValue(13);
	}

	/**
	 * Setter for <code>cora.specimens.external_id</code>.
	 */
	public void setExternalId(String value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>cora.specimens.external_id</code>.
	 */
	public String getExternalId() {
		return (String) getValue(14);
	}

	/**
	 * Setter for <code>cora.specimens.external_label</code>.
	 */
	public void setExternalLabel(String value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>cora.specimens.external_label</code>.
	 */
	public String getExternalLabel() {
		return (String) getValue(15);
	}

	/**
	 * Setter for <code>cora.specimens.rejection_jeopardy</code>.
	 */
	public void setRejectionJeopardy(String value) {
		setValue(16, value);
	}

	/**
	 * Getter for <code>cora.specimens.rejection_jeopardy</code>.
	 */
	public String getRejectionJeopardy() {
		return (String) getValue(16);
	}

	/**
	 * Setter for <code>cora.specimens.rejection_criteria</code>.
	 */
	public void setRejectionCriteria(String value) {
		setValue(17, value);
	}

	/**
	 * Getter for <code>cora.specimens.rejection_criteria</code>.
	 */
	public String getRejectionCriteria() {
		return (String) getValue(17);
	}

	/**
	 * Setter for <code>cora.specimens.active</code>.
	 */
	public void setActive(Boolean value) {
		setValue(18, value);
	}

	/**
	 * Getter for <code>cora.specimens.active</code>.
	 */
	public Boolean getActive() {
		return (Boolean) getValue(18);
	}

	/**
	 * Setter for <code>cora.specimens.version</code>.
	 */
	public void setVersion(Long value) {
		setValue(19, value);
	}

	/**
	 * Getter for <code>cora.specimens.version</code>.
	 */
	public Long getVersion() {
		return (Long) getValue(19);
	}

	/**
	 * Setter for <code>cora.specimens.created</code>.
	 */
	public void setCreated(Timestamp value) {
		setValue(20, value);
	}

	/**
	 * Getter for <code>cora.specimens.created</code>.
	 */
	public Timestamp getCreated() {
		return (Timestamp) getValue(20);
	}

	/**
	 * Setter for <code>cora.specimens.modified</code>.
	 */
	public void setModified(Timestamp value) {
		setValue(21, value);
	}

	/**
	 * Getter for <code>cora.specimens.modified</code>.
	 */
	public Timestamp getModified() {
		return (Timestamp) getValue(21);
	}

	/**
	 * Setter for <code>cora.specimens.created_by</code>.
	 */
	public void setCreatedBy(UUID value) {
		setValue(22, value);
	}

	/**
	 * Getter for <code>cora.specimens.created_by</code>.
	 */
	public UUID getCreatedBy() {
		return (UUID) getValue(22);
	}

	/**
	 * Setter for <code>cora.specimens.modified_by</code>.
	 */
	public void setModifiedBy(UUID value) {
		setValue(23, value);
	}

	/**
	 * Getter for <code>cora.specimens.modified_by</code>.
	 */
	public UUID getModifiedBy() {
		return (UUID) getValue(23);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<UUID> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached SpecimensRecord
	 */
	public SpecimensRecord() {
		super(Specimens.SPECIMENS);
	}

	/**
	 * Create a detached, initialised SpecimensRecord
	 */
	public SpecimensRecord(UUID id, UUID orderId, String sampleType, Timestamp arrivalDate, Timestamp reconciliationDate, Timestamp collectionDate, Timestamp retrievalDate, String sourceType, String rationale, String anticoagulant, Integer quantityReceived, Double gdnaConcentration, Double cellCount, Double concentration, String externalId, String externalLabel, String rejectionJeopardy, String rejectionCriteria, Boolean active, Long version, Timestamp created, Timestamp modified, UUID createdBy, UUID modifiedBy) {
		super(Specimens.SPECIMENS);

		setValue(0, id);
		setValue(1, orderId);
		setValue(2, sampleType);
		setValue(3, arrivalDate);
		setValue(4, reconciliationDate);
		setValue(5, collectionDate);
		setValue(6, retrievalDate);
		setValue(7, sourceType);
		setValue(8, rationale);
		setValue(9, anticoagulant);
		setValue(10, quantityReceived);
		setValue(11, gdnaConcentration);
		setValue(12, cellCount);
		setValue(13, concentration);
		setValue(14, externalId);
		setValue(15, externalLabel);
		setValue(16, rejectionJeopardy);
		setValue(17, rejectionCriteria);
		setValue(18, active);
		setValue(19, version);
		setValue(20, created);
		setValue(21, modified);
		setValue(22, createdBy);
		setValue(23, modifiedBy);
	}
}
