package at.htlkaindorf.adapter;

public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new VLCAdapter();
        player.play("example.vlc");
    }
}
