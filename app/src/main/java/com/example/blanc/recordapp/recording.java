package com.example.blanc.recordapp;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class recording extends AppCompatActivity {

    private static final int RECORDER_SAMPLERATE = 44100;
    private String dossier="";

    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;

    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    private AudioRecord recorder = null;
    private Thread recordingThread = null;
    private boolean isRecording = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        setButtonHandlers();
        enableButtons(false);

        int bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE,
                RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);






        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {



            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                if(checkedId == R.id.avance) {

                   dossier="avance/";
                    boolean repDejaExistant = false;
                    File sdcard = new File("/sdcard");
                    String[] contenuSdCard = sdcard.list();
                    int i=0;
                    while(i<contenuSdCard.length && !repDejaExistant){
                        if(contenuSdCard[i].compareTo("avance")==0)
                            repDejaExistant = true;
                        i++;
                    }
                    File rep = new File("/sdcard/avance");
                    if(!repDejaExistant)
                        rep.mkdirs();

                } else if(checkedId == R.id.recule) {

                    dossier="recule/";
                    boolean repDejaExistant = false;
                    File sdcard = new File("/sdcard");
                    String[] contenuSdCard = sdcard.list();
                    int i=0;
                    while(i<contenuSdCard.length && !repDejaExistant){
                        if(contenuSdCard[i].compareTo("recule")==0)
                            repDejaExistant = true;
                        i++;
                    }
                    File rep = new File("/sdcard/recule");
                    if(!repDejaExistant)
                        rep.mkdirs();

                } else if(checkedId == R.id.droite) {

                    dossier="droite/";
                    boolean repDejaExistant = false;
                    File sdcard = new File("/sdcard");
                    String[] contenuSdCard = sdcard.list();
                    int i=0;
                    while(i<contenuSdCard.length && !repDejaExistant){
                        if(contenuSdCard[i].compareTo("droite")==0)
                            repDejaExistant = true;
                        i++;
                    }
                    File rep = new File("/sdcard/droite");
                    if(!repDejaExistant)
                        rep.mkdirs();

                }else if(checkedId == R.id.gauche) {

                    dossier="gauche/";
                    boolean repDejaExistant = false;
                    File sdcard = new File("/sdcard");
                    String[] contenuSdCard = sdcard.list();
                    int i=0;
                    while(i<contenuSdCard.length && !repDejaExistant){
                        if(contenuSdCard[i].compareTo("gauche")==0)
                            repDejaExistant = true;
                        i++;
                    }
                    File rep = new File("/sdcard/gauche");
                    if(!repDejaExistant)
                        rep.mkdirs();

                }

                else if(checkedId == R.id.urgence) {

                    dossier="urgence/";
                    boolean repDejaExistant = false;
                    File sdcard = new File("/sdcard");
                    String[] contenuSdCard = sdcard.list();
                    int i=0;
                    while(i<contenuSdCard.length && !repDejaExistant){
                        if(contenuSdCard[i].compareTo("urgence")==0)
                            repDejaExistant = true;
                        i++;
                    }
                    File rep = new File("/sdcard/urgence");
                    if(!repDejaExistant)
                        rep.mkdirs();

                }

                else if(checkedId == R.id.tourned) {

                    dossier="tourneGauche/";
                    boolean repDejaExistant = false;
                    File sdcard = new File("/sdcard");
                    String[] contenuSdCard = sdcard.list();
                    int i=0;
                    while(i<contenuSdCard.length && !repDejaExistant){
                        if(contenuSdCard[i].compareTo("tourneGauche")==0)
                            repDejaExistant = true;
                        i++;
                    }
                    File rep = new File("/sdcard/tourneGauche");
                    if(!repDejaExistant)
                        rep.mkdirs();

                }
                else if(checkedId == R.id.flip) {

                    dossier="flip/";
                    boolean repDejaExistant = false;
                    File sdcard = new File("/sdcard");
                    String[] contenuSdCard = sdcard.list();
                    int i=0;
                    while(i<contenuSdCard.length && !repDejaExistant){
                        if(contenuSdCard[i].compareTo("flip")==0)
                            repDejaExistant = true;
                        i++;
                    }
                    File rep = new File("/sdcard/flip");
                    if(!repDejaExistant)
                        rep.mkdirs();

                }
                else if(checkedId == R.id.arete) {

                    dossier="areteToi/";
                    boolean repDejaExistant = false;
                    File sdcard = new File("/sdcard");
                    String[] contenuSdCard = sdcard.list();
                    int i=0;
                    while(i<contenuSdCard.length && !repDejaExistant){
                        if(contenuSdCard[i].compareTo("areteToi")==0)
                            repDejaExistant = true;
                        i++;
                    }
                    File rep = new File("/sdcard/areteToi");
                    if(!repDejaExistant)
                        rep.mkdirs();

                }

            }



        });










    }

    private void setButtonHandlers() {
        ((Button) findViewById(R.id.btnStart)).setOnClickListener(btnClick);
        ((Button) findViewById(R.id.btnStop)).setOnClickListener(btnClick);
    }

    private void enableButton(int id, boolean isEnable) {
        ((Button) findViewById(id)).setEnabled(isEnable);
    }

    private void enableButtons(boolean isRecording) {
        enableButton(R.id.btnStart, !isRecording);
        enableButton(R.id.btnStop, isRecording);
    }

    int BufferElements2Rec = 1024; // want to play 2048 (2K) since 2 bytes we use only 1024
    int BytesPerElement = 2; // 2 bytes in 16bit format

    private void startRecording() {

        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
                RECORDER_SAMPLERATE, RECORDER_CHANNELS,
                RECORDER_AUDIO_ENCODING, BufferElements2Rec * BytesPerElement);

        recorder.startRecording();

        isRecording = true;

        recordingThread = new Thread(new Runnable() {

            public void run() {

                try {
                    writeAudioDataToFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, "AudioRecorder Thread");
        recordingThread.start();
    }

    //Conversion of short to byte
    private byte[] short2byte(short[] sData) {
        int shortArrsize = sData.length;
        byte[] bytes = new byte[shortArrsize * 2];

        for (int i = 0; i < shortArrsize; i++) {
            bytes[i * 2] = (byte) (sData[i] & 0x00FF);
            bytes[(i * 2) + 1] = (byte) (sData[i] >> 8);
            sData[i] = 0;
        }
        return bytes;
    }

    private void writeAudioDataToFile() throws IOException {
        // Write the output audio in byte
        EditText edit = (EditText)findViewById(R.id.nomwav);
        String result = edit.getText().toString();

        String filePath = "/sdcard/"+dossier+result+".pcm";
        String filePathwav = "/sdcard/"+dossier+result+".wav";

        short sData[] = new short[BufferElements2Rec];

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (isRecording) {
            // gets the voice output from microphone to byte format
            recorder.read(sData, 0, BufferElements2Rec);
            System.out.println("Short wirting to file" + sData.toString());
            try {
                // writes the data to file from buffer stores the voice buffer
                byte bData[] = short2byte(sData);

                os.write(bData, 0, BufferElements2Rec * BytesPerElement);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File f2 = new File(filePathwav); // The location where you want your WAV file
        File f1 = new File(filePath); // The location where you want your WAV file

        try {
            rawToWave(f1,f2);
            f1.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        // stops the recording activity
        if (null != recorder) {
            isRecording = false;


            recorder.stop();
            recorder.release();

            recorder = null;
            recordingThread = null;
        }
    }

    private View.OnClickListener btnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnStart: {
                    enableButtons(true);
                    startRecording();
                    break;
                }
                case R.id.btnStop: {
                    enableButtons(false);
                    stopRecording();
                    break;
                }
            }
        }
    };

    // onClick of backbutton finishes the activity.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }














    private void rawToWave(final File rawFile, final File waveFile) throws IOException {

        byte[] rawData = new byte[(int) rawFile.length()];
        DataInputStream input = null;
        try {
            input = new DataInputStream(new FileInputStream(rawFile));
            input.read(rawData);
        } finally {
            if (input != null) {
                input.close();
            }
        }

        DataOutputStream output = null;
        try {
            output = new DataOutputStream(new FileOutputStream(waveFile));
            // WAVE header
            // see http://ccrma.stanford.edu/courses/422/projects/WaveFormat/
            writeString(output, "RIFF"); // chunk id
            writeInt(output, 36 + rawData.length); // chunk size
            writeString(output, "WAVE"); // format
            writeString(output, "fmt "); // subchunk 1 id
            writeInt(output, 16); // subchunk 1 size
            writeShort(output, (short) 1); // audio format (1 = PCM)
            writeShort(output, (short) 1); // number of channels
            writeInt(output, 44100); // sample rate
            writeInt(output, RECORDER_SAMPLERATE * 2); // byte rate
            writeShort(output, (short) 2); // block align
            writeShort(output, (short) 16); // bits per sample
            writeString(output, "data"); // subchunk 2 id
            writeInt(output, rawData.length); // subchunk 2 size
            // Audio data (conversion big endian -> little endian)
            short[] shorts = new short[rawData.length / 2];
            ByteBuffer.wrap(rawData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
            ByteBuffer bytes = ByteBuffer.allocate(shorts.length * 2);
            for (short s : shorts) {
                bytes.putShort(s);
            }

            output.write(fullyReadFileToBytes(rawFile));
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
    byte[] fullyReadFileToBytes(File f) throws IOException {
        int size = (int) f.length();
        byte bytes[] = new byte[size];
        byte tmpBuff[] = new byte[size];
        FileInputStream fis= new FileInputStream(f);
        try {

            int read = fis.read(bytes, 0, size);
            if (read < size) {
                int remain = size - read;
                while (remain > 0) {
                    read = fis.read(tmpBuff, 0, remain);
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read);
                    remain -= read;
                }
            }
        }  catch (IOException e){
            throw e;
        } finally {
            fis.close();
        }

        return bytes;
    }
    private void writeInt(final DataOutputStream output, final int value) throws IOException {
        output.write(value >> 0);
        output.write(value >> 8);
        output.write(value >> 16);
        output.write(value >> 24);
    }

    private void writeShort(final DataOutputStream output, final short value) throws IOException {
        output.write(value >> 0);
        output.write(value >> 8);
    }

    private void writeString(final DataOutputStream output, final String value) throws IOException {
        for (int i = 0; i < value.length(); i++) {
            output.write(value.charAt(i));
        }
    }



























































}
