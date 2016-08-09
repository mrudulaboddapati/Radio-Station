package radiostationpd;

/**
 * the list of the items of the playlist
 */
public class PlayListItem
{

	private PlayList playList;
	/**
	 * number of the playlistitem
	 */
	private int playListItemNumber;
	private radiostationpd.Audio audio;

	public int getPlayListItemNumber()
	{
		return this.playListItemNumber;
	}

	public void setPlayListItemNumber(int playListItemNumber)
	{
		this.playListItemNumber = playListItemNumber;
	}

}