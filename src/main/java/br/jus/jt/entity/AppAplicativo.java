package br.jus.jt.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the APP_APLICATIVO database table.
 * 
 */
@Entity
@Table(name="APP_APLICATIVO",schema="esb")
@NamedQuery(name="AppAplicativo.findAll", query="SELECT a FROM AppAplicativo a")
public class AppAplicativo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_app", unique=true, nullable=false, precision=22)
	private long idApp;

	@Column(name="NOME_APP", nullable=false, length=100)
	private String nomeApp;

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

	public AppAplicativo() {
	}	

	public AppAplicativo(long idApp) {
		super();
		this.idApp = idApp;
	}

	public long getIdApp() {
		return this.idApp;
	}

	public void setIdApp(long idApp) {
		this.idApp = idApp;
	}

	public String getNomeApp() {
		return this.nomeApp;
	}

	public void setNomeApp(String nomeApp) {
		this.nomeApp = nomeApp;
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
