package mapwriter.tasks;

import mapwriter.region.RegionManager;

public class CloseRegionManagerTask extends Task
{

	private final RegionManager regionManager;

	public CloseRegionManagerTask(RegionManager regionManager)
	{
		this.regionManager = regionManager;
	}

	@Override
	public boolean CheckForDuplicate()
	{
		return false;
	}

	@Override
	public void onComplete()
	{
	}

	@Override
	public void run()
	{
		this.regionManager.close();
	}
}
