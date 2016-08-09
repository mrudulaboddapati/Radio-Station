package radiostationpd;

/**
 * a notice appearing in a newspaper or public place
 */
public class Announcement extends Audio
{

	/**
	 * genre of the announcement
	 */
	private String announcementGenre;
	/**
	 * artist of the announcement
	 */
	private String announcementArtist;

	public String getAnnouncementGenre()
	{
		return this.announcementGenre;
	}

	public void setAnnouncementGenre(String announcementGenre)
	{
		this.announcementGenre = announcementGenre;
	}

	public String getAnnouncementArtist()
	{
		return this.announcementArtist;
	}

	public void setAnnouncementArtist(String announcementArtist)
	{
		this.announcementArtist = announcementArtist;
	}

}