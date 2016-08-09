package radiostationpd;

/**
 * public service announcement
 */
public class PSA extends Audio
{

	/**
	 * organization of the PSA
	 */
	private String organization;

	public String getOrganization()
	{
		return this.organization;
	}

	public void setOrganization(String organization)
	{
		this.organization = organization;
	}

}