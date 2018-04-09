package com.ru.util.grab;

import com.ru.ebooks.entity.EbookChapter;
import com.ru.ebooks.entity.Ebooks;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 * 爬取书籍
 */
public class GrabEbook {
    /**
     * 私有化构造方法
     */
    private GrabEbook(){}

    private static  GrabEbook instance=null;

    /**
     *  静态工厂方法
     */
    public synchronized   static GrabEbook getInstance(){
       if(instance==null){
           instance=new GrabEbook();
       }
        return instance;
    }

    /**
     * 数据网址
     */
    public static final String books_url="https://www.37zw.net/xiaoshuodaquan/";//37

    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
    * 获取书名及地址
    * @return
    * @throws java.io.IOException
    */
	public static List<Ebooks> getEbooks() throws IOException {
		List<Ebooks> list=new ArrayList<Ebooks>();
		//获取内容
		Document doc= Jsoup.connect(books_url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0)").get();
		Element content = doc.getElementById("main");
		Elements links = content.getElementsByTag("li");
		 for (Element e:links) {
			 String []name=e.text().split("/");
			 String second_url=e.select("a").attr("abs:href");//书籍详情地址
		     Ebooks ebooks=new Ebooks();
		     ebooks.setName(name[0]);//书名
		     ebooks.setWriter(name[1]);
		     ebooks.setCopyurl(second_url);//来源网址
		     ebooks.setCreatetime(sdf.format(new Date()));
		     list.add(ebooks);
		}
		 return list;
	}

    /**
     * 获取章节
    * @param ebook
    * @param num
    * @return
            * @throws Exception
    */
    public static Ebooks getChapter(Ebooks ebook,int num) throws Exception{
        Document document=Jsoup.connect(ebook.getCopyurl()).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0)").get();
        Elements chapters=document.getElementById("list").getElementsByTag("dd").select("a");
        String lasttime=document.getElementById("info").getElementsByTag("p").get(2).text().split("：")[1];
        String type=document.getElementsByClass("con_top").text().split(">")[1].trim();
        ebook.setType(type);//小说类型
        ebook.setUpdatetime(lasttime);//最后更新时间
        ebook.setIntro(document.getElementById("intro").getElementsByTag("p").text());//简介
        String cover= document.getElementById("fmimg").select("img").attr("abs:src");//封面
        ebook.setCover(cover);
        List<EbookChapter> list=new ArrayList<EbookChapter>();
        int i=1;
        for (Element e:chapters) {
            String second_url=e.attr("abs:href");//内容详情地址
            String chaptername=e.text();
            if(i>num){
                EbookChapter chapter=new EbookChapter();
                chapter.setChapter(chaptername);
                chapter.setBookname(ebook.getName());
                chapter.setEbookid(ebook.getId());
                chapter.setPri(i);
                chapter.setCopyurl(second_url);
           /*     try {
                    setChapterContent(chapter);
                } catch (Exception e2) {
                    System.out.println(e2);
                }*/
                list.add(chapter);
            }
            i++;
        }
        ebook.setChapters(list);
        return ebook;
    }

    /**
     * 获取章节内容
    * @param chapter
    * @return
     * @throws IOException
    */
    public static void setChapterContent(EbookChapter chapter) throws IOException{
        Document contentDocument=Jsoup.connect(chapter.getCopyurl()).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0)").get();
        String content=contentDocument.getElementById("content").text();
        chapter.setContent(content);
    }


}
