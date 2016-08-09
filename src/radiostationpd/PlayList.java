package radiostationpd;

import java.util.ArrayList;
import java.util.Date;

/**
 * a list or pieces of music chosen to broadcast
 */
public class PlayList
{

	private Station station;
	private ArrayList<PlayListItem> playListItems;
	/**
	 * description of the playlist
	 */
	private String playListDescription;
	/**
	 * duration of the playlist
	 */
	private int playListDuration;
	/**
	 * the date of creation of the playlist
	 */
	private Date playListCreationDate;
	/**
	 * genre of the playlist
	 */
	private String playListGenre;
	/**
	 * the artist of the playlist
	 */
	private String playListArtist;
	/**
	 * the date and time of the playlist
	 */
	private Date playListDateTime;

	public String getPlayListDescription()
	{
		return this.playListDescription;
	}

	public void setPlayListDescription(String playListDescription)
	{
		this.playListDescription = playListDescription;
	}

	public int getPlayListDuration()
	{
		return this.playListDuration;
	}

	public void setPlayListDuration(int playListDuration)
	{
		this.playListDuration = playListDuration;
	}

	public Date getPlayListCreationDate()
	{
		return this.playListCreationDate;
	}

	public void setPlayListCreationDate(Date playListCreationDate)
	{
		this.playListCreationDate = playListCreationDate;
	}

	public String getPlayListGenre()
	{
		return this.playListGenre;
	}

	public void setPlayListGenre(String playListGenre)
	{
		this.playListGenre = playListGenre;
	}

	public String getPlayListArtist()
	{
		return this.playListArtist;
	}

	public void setPlayListArtist(String playListArtist)
	{
		this.playListArtist = playListArtist;
	}

	public Date getPlayListDateTime()
	{
		return this.playListDateTime;
	}

	public void setPlayListDateTime(Date playListDateTime)
	{
		this.playListDateTime = playListDateTime;
	}

}