package ro.mygallery.mygallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Cell> allFilesPaths;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        GridView gridView = (GridView) findViewById(R.id.gridview);
//        gridView.setAdapter(new ImageAdapter(this));
//
//        TextView textView  = (TextView) findViewById(R.id.textView);
//        ImageAdapter2 pathAbs = new ImageAdapter2();
//        File dir = Environment.getExternalStorageDirectory();
//        String path = dir.getAbsolutePath();
//
//        textView.setText(path);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);

        }else
        {
            showImages();
        }


    }

    private void showImages() {
        //This is the folder with all the images
        String path =Environment.getExternalStorageDirectory().getAbsolutePath() +"/DCIM/Camera";
        allFilesPaths = new ArrayList<>();
        allFilesPaths = listAllFiles(path);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        //make the list with three colums.
        RecyclerView.LayoutManager layoutManager =new  GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Cell> cells = prepareData();
        MyAdapter adapter =new MyAdapter(getApplicationContext(), cells);
        recyclerView.setAdapter(adapter);


    }
    private ArrayList<Cell> prepareData(){
        ArrayList<Cell> alImages = new ArrayList<>();
        for (Cell c: allFilesPaths)
        {
            Cell cell   = new Cell();
            cell.setTitle(c.getTitle());
            cell.setPath(c.getPath());
            alImages.add(cell);
        }
        return  alImages;
    }

    private List<Cell> listAllFiles(String pathName) {
        List<Cell> allFiles = new ArrayList<>();
        File file = new File(pathName);
        File[] files = file.listFiles();
        if(files !=null)
        {
            for(File f : files)
            {
                Cell cell =new Cell();
                cell.setTitle(f.getName());
                cell.setPath(f.getAbsolutePath());
                allFiles.add(cell);
            }
        }
        return  allFiles;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                showImages();
            }else
            {
                Toast.makeText(this,"Permision not granted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
