package entryFactory;

public interface EntryFactory<L> {

	/**
	 * 构造计划项的工厂方法，根据输入的特定格式的包含一个计划项所有信息的字符串，构造一个新的计划项并返回
	 * @param S 具有特定格式的包含一个计划项所有信息的字符串
	 * @return 生成的计划项
	 */
	public L getEntry(String S);
}
