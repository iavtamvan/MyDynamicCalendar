package com.desai.vatsal.mydynamiccalendarexample.Model;

import com.google.gson.annotations.SerializedName;

public class TransaksiModel {

	@SerializedName("nama_sales")
	private String namaSales;

	@SerializedName("tgl_jatuh_tempo")
	private String tglJatuhTempo;

	@SerializedName("jumlah_roti")
	private String jumlahRoti;

	@SerializedName("nama_outlet")
	private String namaOutlet;

	@SerializedName("hutang_pembayaran")
	private String hutangPembayaran;

	@SerializedName("aksi_transaksi")
	private String aksiTransaksi;

	@SerializedName("nominal_pembayaran")
	private String nominalPembayaran;

	@SerializedName("tgl_transaksi")
	private String tglTransaksi;

	@SerializedName("id_nota")
	private String idNota;

	@SerializedName("index_transaksi")
	private String indexTransaksi;

	@SerializedName("jumlah_bayar")
	private String jumlahBayar;

	@SerializedName("harga_roti")
	private String hargaRoti;

	@SerializedName("nama_roti")
	private String namaRoti;

	public void setNamaSales(String namaSales){
		this.namaSales = namaSales;
	}

	public String getNamaSales(){
		return namaSales;
	}

	public void setTglJatuhTempo(String tglJatuhTempo){
		this.tglJatuhTempo = tglJatuhTempo;
	}

	public String getTglJatuhTempo(){
		return tglJatuhTempo;
	}

	public void setJumlahRoti(String jumlahRoti){
		this.jumlahRoti = jumlahRoti;
	}

	public String getJumlahRoti(){
		return jumlahRoti;
	}

	public void setNamaOutlet(String namaOutlet){
		this.namaOutlet = namaOutlet;
	}

	public String getNamaOutlet(){
		return namaOutlet;
	}

	public void setHutangPembayaran(String hutangPembayaran){
		this.hutangPembayaran = hutangPembayaran;
	}

	public String getHutangPembayaran(){
		return hutangPembayaran;
	}

	public void setAksiTransaksi(String aksiTransaksi){
		this.aksiTransaksi = aksiTransaksi;
	}

	public String getAksiTransaksi(){
		return aksiTransaksi;
	}

	public void setNominalPembayaran(String nominalPembayaran){
		this.nominalPembayaran = nominalPembayaran;
	}

	public String getNominalPembayaran(){
		return nominalPembayaran;
	}

	public void setTglTransaksi(String tglTransaksi){
		this.tglTransaksi = tglTransaksi;
	}

	public String getTglTransaksi(){
		return tglTransaksi;
	}

	public void setIdNota(String idNota){
		this.idNota = idNota;
	}

	public String getIdNota(){
		return idNota;
	}

	public void setIndexTransaksi(String indexTransaksi){
		this.indexTransaksi = indexTransaksi;
	}

	public String getIndexTransaksi(){
		return indexTransaksi;
	}

	public void setJumlahBayar(String jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public String getJumlahBayar(){
		return jumlahBayar;
	}

	public void setHargaRoti(String hargaRoti){
		this.hargaRoti = hargaRoti;
	}

	public String getHargaRoti(){
		return hargaRoti;
	}

	public void setNamaRoti(String namaRoti){
		this.namaRoti = namaRoti;
	}

	public String getNamaRoti(){
		return namaRoti;
	}
}