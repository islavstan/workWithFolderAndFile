package com.islavdroid.createfolder;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      createFolder("Sta");
        delete("/mnt/sdcard/StasDasha");
    }
//создание
    public void createFolder(String folder){
        File f1 = new File(Environment.getExternalStorageDirectory()+"/StasDasha/"+folder); //Создаем файловую переменную
        //Environment.getExternalStorageDirectory()-получаем путь к сд карте
        //   /StasDasha/ уже созданная папка
        // если нужно создать новую папку то пишем Environment.getExternalStorageDirectory()+"/"+folder
        if (!f1.exists()) { //Если папка не существует
            f1.mkdirs() ;//создаем её
            Toast.makeText(this,"создано",Toast.LENGTH_SHORT).show();
        }}

   //Копирование файла или директории:
    public  boolean copy(String from, String to) {
        try {
            File fFrom = new File(from);
            if (fFrom.isDirectory()) { // Если директория, копируем все ее содержимое
                createFolder(to);
                String[] FilesList = fFrom.list();
                for (int i = 0; i <= FilesList.length; i++)
                    if (!copy(from + "/" + FilesList[i], to + "/" + FilesList[i]))
                        return false; // Если при копировании произошла ошибка
            } else if (fFrom.isFile()) { // Если файл просто копируем его
                File fTo = new File(to);
                InputStream in = new FileInputStream(fFrom); // Создаем потоки
                OutputStream out = new FileOutputStream(fTo);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close(); // Закрываем потоки
                out.close();
            }
        } catch (FileNotFoundException ex) { // Обработка ошибок
        } catch (IOException e) { // Обработка ошибок
        }
        return true; // При удачной операции возвращаем true
    }
//удаление
        public  void delete(String path) {
            File file = new File(path); //Создаем файловую переменную
            if (file.exists()) { //Если файл или директория существует
                String deleteCmd = "rm -r " + path; //Создаем текстовую командную строку
                Runtime runtime = Runtime.getRuntime();

                try {
                    runtime.exec(deleteCmd);//Выполняем системные команды

                } catch (IOException e) {
                }
            }
        }







    //Перемещение файла или директории:
    private boolean move(String from,String to) {
        try {
            File fFrom = new File(from);
            if (fFrom.isDirectory()) { // Если директория, копируем все ее содержимое
                createFolder(to);
                String[] FilesList = fFrom.list();
                for (int i = 0; i <= FilesList.length; i++)
                    if (!copy(from + "/" + FilesList[i], to + "/" + FilesList[i]))
                        return false; // Если при копировании произошла ошибка
            } else if (fFrom.isFile()) { // Если файл просто копируем его
                File fTo = new File(to);
                InputStream in = new FileInputStream(fFrom); // Создаем потоки
                OutputStream out = new FileOutputStream(fTo);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close(); // Закрываем потоки
                out.close();
            }
        } catch (FileNotFoundException ex) { // Обработка ошибок
        } catch (IOException e) { // Обработка ошибок
        }
        String deleteCmd = "rm -r " + from; //Создаем текстовую командную строку в которой удаляем начальный файл
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(deleteCmd); //Выполняем удаление с помощью команд
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true; // При удачной операции возвращаем true
    }



}
