package radiostationpd;

/**
 * a piece of music used in a movie or an album
 */
public class Track extends Audio
{

	/**
	 * artist of the track
	 */
	private String trackArtist;
	/**
	 * album of the track
	 */
	private String trackAlbum;
	/**
	 * the serial number of the track
	 */
	private int trackNumber;
	/**
	 * genre of the track
	 */
	private String trackGenre;

	public String getTrackArtist()
	{
		return this.trackArtist;
	}

	public void setTrackArtist(String trackArtist)
	{
		this.trackArtist = trackArtist;
	}

	public String getTrackAlbum()
	{
		return this.trackAlbum;
	}

	public void setTrackAlbum(String trackAlbum)
	{
		this.trackAlbum = trackAlbum;
	}

	public int getTrackNumber()
	{
		return this.trackNumber;
	}

	public void setTrackNumber(int trackNumber)
	{
		this.trackNumber = trackNumber;
	}

	public String getTrackGenre()
	{
		return this.trackGenre;
	}

	public void setTrackGenre(String trackGenre)
	{
		this.trackGenre = trackGenre;
	}

}