package radiostationpd;

import java.util.ArrayList;

/**
 * set of equipment necessary to carry on communication
 */
public class Station
{

	private ArrayList<Audio> audios;
	private ArrayList<PlayList> playLists;
	/**
	 * name of the station
	 */
	private String stationName;
	/**
	 * URL of the station
	 */
	private String stationURL;
	/**
	 * organization of the PSA
	 */
	private String organization;
    public Station() 
    {
    	audios=new ArrayList<Audio>();
    	playLists=new ArrayList<PlayList>();
    	    	  		
    	    
    }
    public void addAudio(Audio audio)
    {
    	audios.add(audio);
    
    }
    public void removeAudio(Audio audio)
    {
    	audios.remove(audio);
    
    }
    public ArrayList <Audio> getAudios()
    {
    	return audios;
    }
    public void addPlayList(PlayList playlist)
    {
    	playLists.add(playlist);
    
    }
    public void removePlayList(PlayList playlist)
    {
    	playLists.remove(playlist);
    
    }
    public ArrayList <PlayList> getPlayLists()
    {
    	return playLists;
    }
	public String getStationName()
	{
		return this.stationName;
	}

	public void setStationName(String stationName)
	{
		this.stationName = stationName;
	}

	public String getStationURL()
	{
		return this.stationURL;
	}

	public void setStationURL(String stationURL)
	{
		this.stationURL = stationURL;
	}

	public String getOrganization()
	{
		return this.organization;
	}

	public void setOrganization(String organization)
	{
		this.organization = organization;
	}

}