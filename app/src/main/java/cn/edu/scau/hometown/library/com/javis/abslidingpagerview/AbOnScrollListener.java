package cn.edu.scau.hometown.library.com.javis.abslidingpagerview;


/**
 * ���ƣ�AbOnScrollListener.java 
 * ����������������
 */
public interface AbOnScrollListener {
    
    /**
     * ����.
     * @param arg1 ���ز���
     */
    public void onScroll(int arg1); 
    
    /**
	 * ����ֹͣ.
	 */
    public void onScrollStoped();

	/**
	 * �����������.
	 */
    public void onScrollToLeft();

	/**
	 * ���������ұ�.
	 */
    public void onScrollToRight();

}
