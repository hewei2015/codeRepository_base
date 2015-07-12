package hw.learn.advance.designmodel.agency.liuwei_xml_raw;

public interface AbstractPermission {
	/**
	 * 修改用户信息
	 */
	public void modifyUserInfo();

	/**
	 * 查看帖子
	 */
	public void viewNote();

	/**
	 * 发布帖子
	 */
	public void publishNote();

	/**
	 * 修改帖子
	 */
	public void modifyNote();

	/**
	 * 设置权限
	 * @param level 权限码
	 */
	public void setLevel(int level);
}