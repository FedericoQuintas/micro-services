package de.bonify.news.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "T_NEWS", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class NewsPersistenceModel {

	@Column(name="channel", length=11)
	private Long channelId;
	
	@Id
	@Column(name="id", nullable=false, unique=true, length=11)
	private Long id;

	public NewsPersistenceModel() {
	}
	
	public NewsPersistenceModel(Long channelId, Long id) {
		this.channelId = channelId;
		this.id = id;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChannelId() {
		return this.channelId;
	}

	public Long getId() {
		return this.id;
	}

}
