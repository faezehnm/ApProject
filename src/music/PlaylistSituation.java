package music;

import java.io.Serializable;

/**
 * This enum the situation of a playlist. Temporary situation for playlists thar are created by user
 * and permanent situatuin for playlists that are created by default and can<t be deleted.
 *
 * @author Seyedeh Fatemeh Ahmadzadeh
 * @since 2019
 * @version 1.0
 */

public enum PlaylistSituation implements Serializable {
    PERMANENT , TEMPORARY
}
