package radiostationpd;

import java.util.Date;

/**
 * sound that is recorded especially when transmitted or reproduced
 */
public abstract class Audio
{

	private Station station;
	/**
	 * ID of the audio
	 */
	private int audioaudioID;
	/**
	 * description of the audio
	 */
	private String audiodescription;
	/**
	 * duration of the audio
	 */
	private int audioduration;
	/**
	 * name of the audio file
	 */
	private String audioFileName;
	/**
	 * the last date or time the audio was played
	 */
	private Date audioLastDateTimePlayed;

	public int getAudioaudioID()
	{
		return this.audioaudioID;
	}

	public void setAudioaudioID(int audioaudioID)
	{
		this.audioaudioID = audioaudioID;
	}

	public String getAudiodescription()
	{
		return this.audiodescription;
	}

	public void setAudiodescription(String audiodescription)
	{
		this.audiodescription = audiodescription;
	}

	public int getAudioduration()
	{
		return this.audioduration;
	}

	public void setAudioduration(int audioduration)
	{
		this.audioduration = audioduration;
	}

	public String getAudioFileName()
	{
		return this.audioFileName;
	}

	public void setAudioFileName(String audioFileName)
	{
		this.audioFileName = audioFileName;
	}

	public Date getAudioLastDateTimePlayed()
	{
		return this.audioLastDateTimePlayed;
	}

	public void setAudioLastDateTimePlayed(Date audioLastDateTimePlayed)
	{
		this.audioLastDateTimePlayed = audioLastDateTimePlayed;
	}

}