/*获取饼图的数据*/
package com.sy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.sy.hibernate.bus_item;
import com.sy.hibernate.campaign_item;
import com.sy.util.myutil;

public class piechartdao {
	/*通过获取sql语句，
	 * 最大显示数已经目前的页码来获取数据库存储的数据
	 * 并格式化返回给前台。
	 */
	public static Map<String,Double> getchartdata(String tabname) {
		String hql = "from bus_item";
		if (tabname.length()>4) {
			hql = "from campaign_item";
		}
		List result = null;
		Configuration cfg = new AnnotationConfiguration();
		SessionFactory sf = cfg.configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		result = session.createQuery(hql).list();
		try {
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
			sf.close();
		}
		Map<String, Double> capcont = new HashMap<String, Double>();
		Object obj = result.get(0);
		if (obj.getClass().getName().equals("com.sy.hibernate.campaign_item")) {
			int nub=result.size();
			for (Object i : result) {
				String q = ((campaign_item) i).getCampaign();
				if (capcont.containsKey(q)) {
					capcont.put(q, capcont.get(q) + 1);
				} else {
					capcont.put(q, 1.00);
				}
			}
			Set<String> keys=capcont.keySet();
			for (String key:keys) {
				capcont.put(key, capcont.get(key)/nub*100);
			}
		}else{
			int nub=result.size();
			for (Object i : result) {
				String q = ((bus_item) i).getBusiness();
				if (capcont.containsKey(q)) {
					capcont.put(q, capcont.get(q) + 1);
				} else {
					capcont.put(q, 1.00);
				}
			}
			Set<String> keys=capcont.keySet();
			for (String key:keys) {
				capcont.put(key, capcont.get(key)/nub*100);
			}
		}
		return myutil.toConfigMap(capcont, 15);
	}

}
