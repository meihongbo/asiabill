package com.asiabill.framework.base;

/**
 * 表单提交参数类
 * @author 123
 *
 */
/**
 * @author 123
 */
public class DataTablePageReq implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;

	/*------------------DT自动请求的参数(Sent parameters) begin--------------------*/  
    /* 
     * 绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回 
     */  
    private Integer draw; // 第几次请求  
    /* 
     * 第一条数据的起始位置，比如0代表第一条数据 
     */  
    private Integer start = 0;// 起止位置  
    /* 
     * 告诉服务器每页显示的条数，这个数字会等于返回的 data集合的记录数，可能会大于因为服务器可能没有那么多数据。 
     * 这个也可能是-1，代表需要返回全部数据(尽管这个和服务器处理的理念有点违背) 
     */  
    private Integer length = 10; // 数据长度  
 
    /* 
     * 告诉后台列排序的方式， desc 降序 asc升序 
     */  
    private String order_dir;  
    private String order_name;
    private String name;
  
   
    /* 
     * 当前页码 
     */  
    private int page_num = 0;    
  
    public DataTablePageReq() { }  
  
    public Integer getDraw() {  
        return draw;  
    }  
  
    public void setDraw(Integer draw) {  
        this.draw = draw;  
    }  
  
    public Integer getStart() {  
        return start;  
    }  
  
    public void setStart(Integer start) {  
        this.start = start;  
    }  
  
    public Integer getLength() {  
        return length;  
    }  
  
    public void setLength(Integer length) {  
        this.length = length;  
    }  
  
   
    public String getOrder_dir() {  
        return order_dir;  
    }  
  
    public void setOrder_dir(String order_dir) {  
        this.order_dir = order_dir;  
    }  
  
    
    public Integer getPage_num() {  
        return page_num;  
    }  
  
    public void setPage_num(Integer page_num) {  
        this.page_num = page_num;  
    }  
  
	public String getOrder_name() {
		return order_name;
	}

	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}  
    
    
}
