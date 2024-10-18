package com.cinntra.vista.activities;


import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.print.PrintAttributes;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.cinntra.vista.BuildConfig;
import com.cinntra.vista.EasyPrefs.Prefs;
import com.cinntra.vista.R;
import com.cinntra.vista.databinding.TestPdfBinding;
import com.cinntra.vista.globals.Globals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class WebViewToPdf extends AppCompatActivity {
    WebView printWeb;
    TestPdfBinding binding;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestPdfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24);

        String PDfFrom = getIntent().getExtras().getString("PDfFrom");
        String PdfID = getIntent().getExtras().getString("PdfID");

        binding.progressBar.setVisibility(View.VISIBLE);

        if (PDfFrom.equalsIgnoreCase("Invoice")) {

            //  http://103.234.187.197:4250/assets/html/invoice.html?id=24&token=20a5228e90bd2cff7b744aa325bf5ec44893a971
            url = Globals.PDFURL + "invoice.html?id=" + PdfID + "&token=" + Prefs.getString(Globals.token, "");

            Log.e("pdfurl", url);
//              url = Globals.PDFURL+"invoice.html?id="+PdfID;
        } else if (PDfFrom.equalsIgnoreCase("Quotation")) {
            url = Globals.PDFURL + "quotation.html?id=" + PdfID + "&token=" + Prefs.getString(Globals.token, "");
            Log.e("pdfurl", url);
        } else if (PDfFrom.equalsIgnoreCase("Order")) {
            url = Globals.PDFURL + "order.html?id=" + PdfID + "&token=" + Prefs.getString(Globals.token, "");
//           url = Globals.PDFURL+"order.html?id="+PdfID;
            Log.e("pdfurl", url);
        }


        // Initializing the WebView
        final WebView webView = (WebView) findViewById(R.id.webViewMain);

        // Initializing the Button
        Button savePdfBtn = (Button) findViewById(R.id.savePdfBtn);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        //  webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        // Setting we View Client
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.progressBar.setVisibility(View.GONE);
                // initializing the printWeb Object
                printWeb = webView;
            }
        });


        webView.loadUrl(url);


        savePdfBtn.setVisibility(View.GONE);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.share:
                String f_name = String.format("%s.pdf", new SimpleDateFormat("dd_MM_yyyyHH_mm_ss", Locale.US).format(new Date()));
                lab_pdf(printWeb, f_name);
//                convertAndShareWebViewContent(url);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private ParcelFileDescriptor getOutputFile(File path, String fileName) {
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path, fileName);
        try {
            file.createNewFile();
            return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_WRITE);
        } catch (Exception e) {
            Log.e("TAG", "Failed to open ParcelFileDescriptor", e);
        }
        return null;
    }

//
//    private void lab_pdf(WebView webView, String f_name) {
//        String path = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/hana/";
////        File f = new File(path);
//
//        File f = new File(path);
//
//        // if (f.exists()) f.delete();
//
//
//        //        try {
//        //            if (!f.getParentFile().exists())
//        //                f.getParentFile().mkdirs();
//        //            if (!f.exists())
//        //                f.createNewFile();
//        //        } catch (IOException e) {
//        //            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        //        }
//
//        final String fileName = f_name;
//
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please wait");
//        progressDialog.show();
////        PdfView obj = new PdfView();
//
//        // PdfViewSHubh.createWebPrintJob(getActivity(),webView,f,fileName,new );
//
//
////        PdfView.createWebPrintJob(this, webView, f, fileName, new PdfView.Callback()
////        {
////
////            @Override
////            public void success(String path) {
////                progressDialog.dismiss();
////                whatsappShare(fileName);
////                //PdfView.openPdfFile(Pdf_Test.this,getString(R.string.app_name),"Do you want to open the pdf file?"+fileName,path);
////            }
////
////            @Override
////            public void failure() {
////                progressDialog.dismiss();
////
////            }
////        });
//    }

    private void lab_pdf(WebView webView, String f_name) {
        // Define the path where the PDF will be stored
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/hana/";

        // Create the directory if it doesn't exist
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory if it doesn't exist
        }

        // Define the file to store the PDF
        File pdfFile = new File(dir, f_name + ".pdf");

        // Show a progress dialog while the PDF is being created
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait, generating PDF...");
        progressDialog.show();

        // Create a PdfDocument and write the WebView content to it
        PdfDocument document = new PdfDocument();

        // Define the size of the page based on the WebView's dimensions
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(webView.getWidth(), webView.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        // Render the WebView content onto the PDF page
        webView.draw(page.getCanvas());

        // Finish the page
        document.finishPage(page);

        // Write the document to the file
        try {
            document.writeTo(new FileOutputStream(pdfFile));
            progressDialog.dismiss();
            Toast.makeText(this, "PDF Created Successfully", Toast.LENGTH_SHORT).show();

            // After creating the PDF, share it via WhatsApp or other platforms
            sharePdf(pdfFile);
        } catch (IOException e) {
            progressDialog.dismiss();
            e.printStackTrace();
            Toast.makeText(this, "Error creating PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            document.close(); // Close the PdfDocument
        }
    }

    private void sharePdf(File pdfFile) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");

        Uri pdfUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", pdfFile);
        shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing PDF...");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Here is the PDF file.");

        // Grant temporary read permission
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Start the sharing intent
        startActivity(Intent.createChooser(shareIntent, "Share PDF via"));
    }








   /* private void whatsappShare(String fName) {
        String stringFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/hana/" + "/" + fName;
//        File file = new File(stringFile);
        File file = getApplicationContext().getExternalFilesDir(stringFile);
        Uri apkURI = FileProvider.getUriForFile(
                this,
                getApplicationContext()
                        .getPackageName() + ".FileProvider", file);


        if (!file.exists()) {
            Toast.makeText(this, "File Not exist", Toast.LENGTH_SHORT).show();

        }
        //    Uri path = Uri.fromFile(file);
        //  Log.e("Path==>", path.toString());
        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, apkURI);
        if (isAppInstalled("com.whatsapp"))
            share.setPackage("com.whatsapp");
        else if (isAppInstalled("com.whatsapp.w4b"))
            share.setPackage("com.whatsapp.w4b");

        startActivity(share);
    }*/


    private void whatsappShare(String fName) {
        String stringFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/hana/" + "/" + fName;
        File file = new File(stringFile);
        Uri apkURI = null;
        try {
            apkURI = FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".FileProvider", file);
        } catch (Exception e) {
            Log.e("whatsapp", "showBottomSheetDialog: ");
            e.printStackTrace();
        }


        if (!file.exists()) {
            Toast.makeText(this, "File Not exist", Toast.LENGTH_SHORT).show();

        /*    try {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("application/pdf");
                share.putExtra(Intent.EXTRA_STREAM, apkURI);
                if (isAppInstalled("com.whatsapp"))
                    share.setPackage("com.whatsapp");
                else if (isAppInstalled("com.whatsapp.w4b"))
                    share.setPackage("com.whatsapp.w4b");

                startActivity(share);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, " WhatsApp is not currently installed on your phone.", Toast.LENGTH_LONG).show();
            }*/

        }
        else {
            try {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("application/pdf");
                share.putExtra(Intent.EXTRA_STREAM, apkURI);
                if (isAppInstalled("com.whatsapp"))
                    share.setPackage("com.whatsapp");
                else if (isAppInstalled("com.whatsapp.w4b"))
                    share.setPackage("com.whatsapp.w4b");

                startActivity(share);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, " WhatsApp is not currently installed on your phone.", Toast.LENGTH_LONG).show();
            }
        }


        //    Uri path = Uri.fromFile(file);
        //  Log.e("Path==>", path.toString());


//        if (share.resolveActivity(getActivity().getPackageManager()) != null) {
//            //startActivity(Intent.createChooser(intent, "Share PDF using"));
//            startActivity(share);
//        } else {
//            startActivity(Intent.createChooser(share, "Share PDF using"));
//        }

    }


    private boolean isAppInstalled(String packageName) {
        try {
            getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException ignored) {
            return false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sharedoc, menu);

        return true;
    }
//
//
    private void convertAndShareWebViewContent(String url) {
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                createPdfAndShare(view);
            }
        });
        webView.loadUrl(url);
    }

    private void createPdfAndShare(WebView webView) {
        PrintAttributes attributes = new PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(new PrintAttributes.Resolution("pdf", "pdf", 600, 600))
                .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
                .build();

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1600, 900, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        webView.draw(canvas);

        document.finishPage(page);

        String filePath = Environment.getExternalStorageDirectory().getPath() + "/webview_content.pdf";
//        String filePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/webview_content.pdf";


        File file = new File(filePath);
        try {
            document.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();

        sharePdfViaWhatsApp(file);
    }



    private void sharePdfViaWhatsApp(File file) {
        Uri uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".FileProvider", file);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        if (isAppInstalled("com.whatsapp"))
            intent.setPackage("com.whatsapp");
        else if (isAppInstalled("com.whatsapp.w4b"))
            intent.setPackage("com.whatsapp.w4b");
        startActivity(Intent.createChooser(intent, "Share PDF via"));
    }


}