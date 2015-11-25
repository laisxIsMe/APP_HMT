package cn.edu.scau.hometown.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.disk.DiskTrimmable;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.concurrent.Executors;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.tools.ImageBuffer;
import cn.edu.scau.hometown.view.CircularProgress;
import uk.co.senab.photoview.PhotoViewAttacher;

/**created by hiai
 *
 *查看图片的activity
 */
public class PhotoViewActivity extends AppCompatActivity implements OnClickListener{

    private String url;
    private static ImageView imageView;
    private static PhotoViewAttacher mAttacher;
    private static CircularProgress progressView;
    private Toolbar toolbar;
    private static  CloseableReference<CloseableStaticBitmap> imageReference;
    private Button savePhoto;
    private static Bitmap bitmap;
    private AlertDialog alertDialog;
    private ViewGroup viewGroup;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_photo_view);
        initView();
        initToolbar();
        url=(String)getIntent().getSerializableExtra("url");
        getImage();


    }


    private void  initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView=(ImageView)findViewById(R.id.imgview);
        progressView = (CircularProgress)findViewById(R.id.Progress_view);
        savePhoto=(Button)findViewById(R.id.savePhoto);
        viewGroup=(ViewGroup)findViewById(R.id.rl_parent);
        savePhoto.setOnClickListener(this);

    }
    private void initToolbar(){

        toolbar.setBackgroundColor(getResources().getColor(R.color.tab_blue));
        setSupportActionBar(toolbar);


    }
    private void getImage(){
       Uri uri = Uri.parse(url);
        ImageRequest request=ImageRequest.fromUri(uri);
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(request, this);

        DataSubscriber dataSubscriber =
                new BaseDataSubscriber<CloseableReference<CloseableStaticBitmap>>() {
                    @Override
                    public void onNewResultImpl(
                            DataSource<CloseableReference<CloseableStaticBitmap>> dataSource) {

                        if (!dataSource.isFinished()) {

                        }

                        CloseableReference<CloseableStaticBitmap> imageReference = dataSource.getResult();
                        if (imageReference != null) {

                            Message message = new Message();
                            message.obj=imageReference;
                            myHandler.sendMessage(message);

                        }
                    }
                    @Override
                    public void onFailureImpl(DataSource dataSource) {
                        Throwable throwable = dataSource.getFailureCause();
                        // handle failure
                    }
                };
        dataSource.subscribe(dataSubscriber, Executors.newSingleThreadExecutor());

    }

    private static Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            imageReference=(CloseableReference<CloseableStaticBitmap>)msg.obj;
            CloseableStaticBitmap image = imageReference.get();
            bitmap=image.getUnderlyingBitmap();
            imageView.setImageBitmap(bitmap);
            mAttacher = new PhotoViewAttacher(imageView);
            progressView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            imageView.invalidate();
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(imageReference!=null)  {
            imageReference.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.savePhoto:

                SavePhoto();

                break;

        }
    }


    private void SavePhoto(){
        showDialog();
        File picFileDir = new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"hongmantang"+File.separator+"红满堂");
       if(!picFileDir.exists()){
             picFileDir.mkdir();
           }

        String photoName= ImageBuffer.convertUrlToFileName(url);
        File file = new File(picFileDir +"/"+photoName);
        try {
            if(file.createNewFile()) {

                OutputStream outStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                outStream.flush();
                outStream.close();

            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            Log.w("test----->", e.toString());
        }

        Toast.makeText(PhotoViewActivity.this, "成功保存图片至/hongmantang/红满堂",
                Toast.LENGTH_SHORT).show();

    }

    private void showDialog(){
//       progressDialog= ProgressDialog.show(PhotoViewActivity.this, "保存", "正在保存,");


//        AlertDialog.Builder builder=new AlertDialog.Builder(PhotoViewActivity.this);
//        View view=getLayoutInflater().
//                inflate(R.layout.photoviewactivity_dialog_layout, null);
//        builder.setView(view);
//        alertDialog=builder.create();
//        alertDialog.show();

    }
}






