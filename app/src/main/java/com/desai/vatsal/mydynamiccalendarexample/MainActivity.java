package com.desai.vatsal.mydynamiccalendarexample;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.EventModel;
import com.desai.vatsal.mydynamiccalendar.GetEventListListener;
import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;
import com.desai.vatsal.mydynamiccalendar.OnEventClickListener;
import com.desai.vatsal.mydynamiccalendar.OnWeekDayViewClickListener;
import com.desai.vatsal.mydynamiccalendarexample.Model.TransaksiModel;
import com.desai.vatsal.mydynamiccalendarexample.Rest.ApiService;
import com.desai.vatsal.mydynamiccalendarexample.Rest.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MyDynamicCalendar myCalendar;

    String convertTgl;
    String idnota;
    String tglTransaksi;
    String tglJatuhTempo;
    String namaoutlet;
    String namaSales;
    String nominalpembayaran;
    String jumlahpembayaran;
    String hutangpembayaran;
    String aksipembayaran;
    String indextransaksi;
    String namaroti;
    String jumlahroti;
    String hargarot;

    private ArrayList<TransaksiModel> transaksiModels;

    private AlertDialog.Builder builder;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        myCalendar = (MyDynamicCalendar) findViewById(R.id.myCalendar);
        setSupportActionBar(toolbar);

        transaksiModels = new ArrayList<>();
        getdata();
//        myCalendar.showMonthView();
        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                myCalendar.getEventList(new GetEventListListener() {
                    @Override
                    public void eventList(ArrayList<EventModel> eventList) {
                        for (int i = 0; i < eventList.size(); i++) {
                            builder = new AlertDialog.Builder(MainActivity.this);
                            inflater = LayoutInflater.from(MainActivity.this);
                            final View dialView = inflater.inflate(R.layout.dialog_box, null);

                            final  TextView sTransaksiHargaRoti;
                            final  TextView sTransaksiJumlahRoti;
                            final  TextView sTransaksiNamaRoti;
                            final  TextView sTransaksiIndexTransaksi;
                            final  TextView sTransaksiAksiTransaksi;
                            final  TextView sTransaksiHutangPembayaran;
                            final  TextView sTransaksiJumlahPembayaran;
                            final  TextView sTransaksiNominalPembayaran;
                            final  TextView sTransaksiNamaSales;
                            final  TextView sTransaksiNamaOutlet;
                            final  TextView sTransaksijatuhTempo;
                            final  TextView sTransaksi;
                            final  TextView sTransaksiIdNota;

                            sTransaksiIdNota = (TextView) dialView.findViewById(R.id.sTransaksiIdNota);
                            sTransaksi = (TextView) dialView.findViewById(R.id.sTransaksi);
                            sTransaksijatuhTempo = (TextView) dialView.findViewById(R.id.sTransaksijatuhTempo);
                            sTransaksiNamaOutlet = (TextView) dialView.findViewById(R.id.sTransaksiNamaOutlet);
                            sTransaksiNamaSales = (TextView) dialView.findViewById(R.id.sTransaksiNamaSales);
                            sTransaksiNominalPembayaran = (TextView) dialView.findViewById(R.id.sTransaksiNominalPembayaran);
                            sTransaksiJumlahPembayaran = (TextView) dialView.findViewById(R.id.sTransaksiJumlahPembayaran);
                            sTransaksiHutangPembayaran = (TextView) dialView.findViewById(R.id.sTransaksiHutangPembayaran);
                            sTransaksiAksiTransaksi = (TextView) dialView.findViewById(R.id.sTransaksiAksiTransaksi);
                            sTransaksiIndexTransaksi = (TextView) dialView.findViewById(R.id.sTransaksiIndexTransaksi);
                            sTransaksiNamaRoti = (TextView) dialView.findViewById(R.id.sTransaksiNamaRoti);
                            sTransaksiJumlahRoti = (TextView) dialView.findViewById(R.id.sTransaksiJumlahRoti);
                            sTransaksiHargaRoti = (TextView) dialView.findViewById(R.id.sTransaksiHargaRoti);

//                            sTransaksiIdNota.setText();


                            Toast.makeText(MainActivity.this, "date  : " + eventList.get(i).getStrName(), Toast.LENGTH_SHORT).show();
                            Log.d("tag", "eventList.getStrName:-" + eventList.get(i).getStrName());
                        }
                    }
                });
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

        myCalendar.setCalendarBackgroundColor("#80d6ff");
//        myCalendar.setCalendarBackgroundColor(R.color.gray);

        myCalendar.setHeaderBackgroundColor("#80d6ff");
//        myCalendar.setHeaderBackgroundColor(R.color.black);

        myCalendar.setHeaderTextColor("#0077c2");
//        myCalendar.setHeaderTextColor(R.color.white);

        myCalendar.setNextPreviousIndicatorColor("#0069c0");
//        myCalendar.setNextPreviousIndicatorColor(R.color.black);

        myCalendar.setWeekDayLayoutBackgroundColor("#965471");
//        myCalendar.setWeekDayLayoutBackgroundColor(R.color.black);

        myCalendar.setWeekDayLayoutTextColor("#246245");
//        myCalendar.setWeekDayLayoutTextColor(R.color.black);

//        myCalendar.isSaturdayOff(true, "#ffffff", "#ff0000");
//        myCalendar.isSaturdayOff(true, R.color.white, R.color.red);

//        myCalendar.isSundayOff(true, "#658745", "#254632");
//        myCalendar.isSundayOff(true, R.color.white, R.color.red);

        myCalendar.setExtraDatesOfMonthBackgroundColor("#324568");
//        myCalendar.setExtraDatesOfMonthBackgroundColor(R.color.black);

        myCalendar.setExtraDatesOfMonthTextColor("#756325");
//        myCalendar.setExtraDatesOfMonthTextColor(R.color.black);

//        myCalendar.setDatesOfMonthBackgroundColor(R.drawable.event_view);
        myCalendar.setDatesOfMonthBackgroundColor("#145687");
//        myCalendar.setDatesOfMonthBackgroundColor(R.color.black);

        myCalendar.setDatesOfMonthTextColor("#745632");
//        myCalendar.setDatesOfMonthTextColor(R.color.black);

//        myCalendar.setCurrentDateBackgroundColor("#123412");
//        myCalendar.setCurrentDateBackgroundColor(R.color.black);

        myCalendar.setCurrentDateTextColor("#00e600");
//        myCalendar.setCurrentDateTextColor(R.color.black);

        myCalendar.setEventCellBackgroundColor("#852365");
//        myCalendar.setEventCellBackgroundColor(R.color.black);

        myCalendar.setEventCellTextColor("#425684");
//        myCalendar.setEventCellTextColor(R.color.black);

//        server ke android
//        try {
//            SimpleDateFormat sdf,sdf2;
//            sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date dateNow = sdf.parse(tgl);
//            sdf2 = new SimpleDateFormat("dd-MM-yyyy");
//            Toast.makeText(this, ""+sdf2.format(dateNow), Toast.LENGTH_SHORT).show();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


//        String tgl = "18-01-2018";
//        myCalendar.addEvent(tgl, "8:00", "8:18", "Baru");
//        myCalendar.addEvent(convertTgl, "8:00", "8:18", "Baru");


//        anroid ke server
//        try {
//            SimpleDateFormat sdf,sdf2;
//            sdf = new SimpleDateFormat("dd-MM-yyyy");
//            Date dateNow = sdf.parse(tgl);
//
//
//            sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//            Toast.makeText(this, ""+sdf2.format(dateNow), Toast.LENGTH_SHORT).show();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


//        myCalendar.addEvent("5-10-2016", "8:00", "8:15", "Today Event 1");
//        myCalendar.addEvent("05-10-2016", "8:15", "8:30", "Today Event 2");
//        myCalendar.addEvent("05-10-2016", "8:30", "8:45", "Today Event 3");
//        myCalendar.addEvent("05-10-2016", "8:45", "9:00", "Today Event 4");
//        myCalendar.addEvent("8-10-2016", "8:00", "8:30", "Today Event 5");
//        myCalendar.addEvent("08-10-2016", "9:00", "10:00", "Today Event 6");

        myCalendar.getEventList(new GetEventListListener() {
            @Override
            public void eventList(ArrayList<EventModel> eventList) {

                Toast.makeText(MainActivity.this, "" + eventList, Toast.LENGTH_SHORT).show();
                Log.e("tag", "eventList.size():-" + eventList.size());
                for (int i = 0; i < eventList.size(); i++) {
                    Toast.makeText(MainActivity.this, "date  : " + eventList.get(i).getStrName(), Toast.LENGTH_SHORT).show();
                    Log.d("tag", "eventList.getStrName:-" + eventList.get(i).getStrName());
                }

            }
        });

//        myCalendar.updateEvent(0, "5-10-2016", "8:00", "8:15", "Today Event 111111");

//        myCalendar.deleteEvent(2);

//        myCalendar.deleteAllEvent();

        myCalendar.setBelowMonthEventTextColor("#425684");
//        myCalendar.setBelowMonthEventTextColor(R.color.black);

        myCalendar.setBelowMonthEventDividerColor("#635478");
//        myCalendar.setBelowMonthEventDividerColor(R.color.black);

        myCalendar.setHolidayCellBackgroundColor("#654248");
//        myCalendar.setHolidayCellBackgroundColor(R.color.black);

        myCalendar.setHolidayCellTextColor("#d590bb");
//        myCalendar.setHolidayCellTextColor(R.color.black);

        myCalendar.setHolidayCellClickable(false);
        myCalendar.addHoliday("2-11-2016");
        myCalendar.addHoliday("8-11-2016");
        myCalendar.addHoliday("12-11-2016");
        myCalendar.addHoliday("13-11-2016");
        myCalendar.addHoliday("8-10-2016");
        myCalendar.addHoliday("10-12-2016");


//        myCalendar.setCalendarDate(5, 10, 2016);

    }

    private void getdata() {
        ApiService apiService = Client.getInstanceRetrofit();
        Call<ArrayList<TransaksiModel>> call = apiService.getTransaksi();
        call.enqueue(new Callback<ArrayList<TransaksiModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TransaksiModel>> call, Response<ArrayList<TransaksiModel>> response) {
                transaksiModels = response.body();
//                Toast.makeText(MainActivity.this, "Response : " + transaksiModels, Toast.LENGTH_SHORT).show();
                for (int i = 0; i < transaksiModels.size(); i++) {
                    idnota               = transaksiModels.get(i).getIdNota();
                    tglTransaksi          = transaksiModels.get(i).getTglTransaksi();
                    tglJatuhTempo            = transaksiModels.get(i).getTglJatuhTempo();
                    namaoutlet           = transaksiModels.get(i).getNamaOutlet();
                    namaSales            = transaksiModels.get(i).getNamaSales();
                    nominalpembayaran     = transaksiModels.get(i).getNominalPembayaran();
                    jumlahpembayaran     = transaksiModels.get(i).getJumlahBayar();
                    hutangpembayaran     = transaksiModels.get(i).getHutangPembayaran();
                    aksipembayaran       = transaksiModels.get(i).getAksiTransaksi();
                    indextransaksi        = transaksiModels.get(i).getIndexTransaksi();
                    namaroti              = transaksiModels.get(i).getNamaRoti();
                    jumlahroti               = transaksiModels.get(i).getJumlahRoti();
                    hargarot                 = transaksiModels.get(i).getHargaRoti();

//                    Toast.makeText(MainActivity.this, "Results : " + tglTransaksi, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this, "Results : " + tglJatuhTempo, Toast.LENGTH_SHORT).show();

                    try {
                        SimpleDateFormat sdf, sdf2;
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateNow = sdf.parse(tglTransaksi);

                        sdf2 = new SimpleDateFormat("dd-MM-yyyy");
//                        Toast.makeText(MainActivity.this, "Fixed "+sdf2.format(dateNow), Toast.LENGTH_SHORT).show();
                        convertTgl = sdf2.format(dateNow);
                        Toast.makeText(MainActivity.this, convertTgl, Toast.LENGTH_SHORT).show();
                        myCalendar.showMonthView();
                        myCalendar.addEvent(convertTgl, "3:23", "8:34", idnota + tglTransaksi + tglJatuhTempo + namaoutlet + namaSales +
                                nominalpembayaran + jumlahpembayaran + hutangpembayaran + aksipembayaran + indextransaksi + namaroti +
                                jumlahroti + hargarot);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

//                    myCalendar.addEvent("18-01-2018", "8:00","8:34", "X");

                }


            }

            @Override
            public void onFailure(Call<ArrayList<TransaksiModel>> call, Throwable t) {
                getdata();
                Toast.makeText(MainActivity.this, "Load Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_month:
                showMonthView();
                return true;
            case R.id.action_month_with_below_events:
                showMonthViewWithBelowEvents();
                return true;
            case R.id.action_week:
                showWeekView();
                return true;
            case R.id.action_day:
                showDayView();
                return true;
            case R.id.action_agenda:
                showAgendaView();
                return true;
            case R.id.action_today:
                myCalendar.goToCurrentDate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showMonthView() {

        myCalendar.showMonthView();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }

    private void showMonthViewWithBelowEvents() {

        myCalendar.showMonthViewWithBelowEvents();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }

    private void showWeekView() {

        myCalendar.showWeekView();

        myCalendar.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {
                Log.e("showWeekView", "from setOnEventClickListener onClick");
            }

            @Override
            public void onLongClick() {
                Log.e("showWeekView", "from setOnEventClickListener onLongClick");

            }
        });

        myCalendar.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
            @Override
            public void onClick(String date, String time) {
                Log.e("showWeekView", "from setOnWeekDayViewClickListener onClick");
                Log.e("tag", "date:-" + date + " time:-" + time);

            }

            @Override
            public void onLongClick(String date, String time) {
                Log.e("showWeekView", "from setOnWeekDayViewClickListener onLongClick");
                Log.e("tag", "date:-" + date + " time:-" + time);

            }
        });


    }

    private void showDayView() {

        myCalendar.showDayView();

        myCalendar.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {
                Log.e("showDayView", "from setOnEventClickListener onClick");

            }

            @Override
            public void onLongClick() {
                Log.e("showDayView", "from setOnEventClickListener onLongClick");

            }
        });

        myCalendar.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
            @Override
            public void onClick(String date, String time) {
                Log.e("showDayView", "from setOnWeekDayViewClickListener onClick");
                Log.e("tag", "date:-" + date + " time:-" + time);
            }

            @Override
            public void onLongClick(String date, String time) {
                Log.e("showDayView", "from setOnWeekDayViewClickListener onLongClick");
                Log.e("tag", "date:-" + date + " time:-" + time);
            }
        });

    }

    private void showAgendaView() {

        myCalendar.showAgendaView();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }


}
