package at.htlkaindorf.adapter;

public class VLCAdapter implements MediaPlayer {

    private VLCPlayer vlcPlayer = new VLCPlayer();

    @Override
    public void play(String file) {

        vlcPlayer.playVLC(file);
    }
}
