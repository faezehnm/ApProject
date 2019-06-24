package display;

import java.io.Serializable;

/**
 * An enum that shows if songs are displaying for playing or for selection of a new playlist
 */

public enum DisplaySongsSituation implements Serializable {
    SELECTION , PLAYING;
}
