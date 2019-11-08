package br.jus.jt.entity;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the APP_APLICATIVO database table.
 * 
 */
@Entity
@Table(name="dialogo",schema="hack")
@NamedQuery(name="Dialogo.findAll", query="SELECT d FROM Dialogo d")
public class Dialogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", unique=true, nullable=false)
	private long id;

	@Column(name="termos", nullable=false)
	private String termos;

//	//bi-directional many-to-one association to AppAplicDevice
//	@OneToMany(mappedBy="appAplicativo")
//	private List<AppAplicDevice> appAplicDevices;
//
//	//bi-directional many-to-one association to AppMensagem
//	@OneToMany(mappedBy="appAplicativo")
//	private List<AppMensagem> appMensagems;
//
//	//bi-directional many-to-one association to AppUsuarioPerfil
//	@OneToMany(mappedBy="appAplicativo")
//	private List<AppUsuarioPerfil> appUsuarioPerfils;

	public Dialogo() {
	}	

	public Dialogo(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTermos() {
		return this.termos;
	}

	public void setTermos(String termos) {
		this.termos = termos;
	}

//	public List<AppAplicDevice> getAppAplicDevices() {
//		return this.appAplicDevices;
//	}
//
//	public void setAppAplicDevices(List<AppAplicDevice> appAplicDevices) {
//		this.appAplicDevices = appAplicDevices;
//	}
//
//	public AppAplicDevice addAppAplicDevice(AppAplicDevice appAplicDevice) {
//		getAppAplicDevices().add(appAplicDevice);
//		appAplicDevice.setAppAplicativo(this);
//
//		return appAplicDevice;
//	}
//
//	public AppAplicDevice removeAppAplicDevice(AppAplicDevice appAplicDevice) {
//		getAppAplicDevices().remove(appAplicDevice);
//		appAplicDevice.setAppAplicativo(null);
//
//		return appAplicDevice;
//	}
//
//	public List<AppMensagem> getAppMensagems() {
//		return this.appMensagems;
//	}
//
//	public void setAppMensagems(List<AppMensagem> appMensagems) {
//		this.appMensagems = appMensagems;
//	}
//
//	public AppMensagem addAppMensagem(AppMensagem appMensagem) {
//		getAppMensagems().add(appMensagem);
//		appMensagem.setAppAplicativo(this);
//
//		return appMensagem;
//	}
//
//	public AppMensagem removeAppMensagem(AppMensagem appMensagem) {
//		getAppMensagems().remove(appMensagem);
//		appMensagem.setAppAplicativo(null);
//
//		return appMensagem;
//	}
//
//	public List<AppUsuarioPerfil> getAppUsuarioPerfils() {
//		return this.appUsuarioPerfils;
//	}
//
//	public void setAppUsuarioPerfils(List<AppUsuarioPerfil> appUsuarioPerfils) {
//		this.appUsuarioPerfils = appUsuarioPerfils;
//	}
//
//	public AppUsuarioPerfil addAppUsuarioPerfil(AppUsuarioPerfil appUsuarioPerfil) {
//		getAppUsuarioPerfils().add(appUsuarioPerfil);
//		appUsuarioPerfil.setAppAplicativo(this);
//
//		return appUsuarioPerfil;
//	}
//
//	public AppUsuarioPerfil removeAppUsuarioPerfil(AppUsuarioPerfil appUsuarioPerfil) {
//		getAppUsuarioPerfils().remove(appUsuarioPerfil);
//		appUsuarioPerfil.setAppAplicativo(null);
//
//		return appUsuarioPerfil;
//	}

}
