package radiostationpd;

/**
 * a notice or announcement in a public medium promoting a product, service, or event or publicizing a job vacancy.
 */
public class Ad extends radiostationpd.Audio
{

	/**
	 * priority of the advertisement
	 */
	private String adPriority;
	/**
	 * client of the advertisement
	 */
	private String adClient;

	public String getAdPriority()
	{
		return this.adPriority;
	}

	public void setAdPriority(String adPriority)
	{
		this.adPriority = adPriority;
	}

	public String getAdClient()
	{
		return this.adClient;
	}

	public void setAdClient(String adClient)
	{
		this.adClient = adClient;
	}

}