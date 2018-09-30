package com.asiabill.framework.base;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;

public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4936466379153191806L;

	private transient ThreadLocal<BaseEntity> visitor = new ThreadLocal<BaseEntity>() {
		protected BaseEntity initialValue() {
			return null;
		}
	};

	private String simpleName = getClass().getSimpleName();

	private String toString0() {
		try {
			PropertyDescriptor[] props = BeanUtils.getPropertyDescriptors(getClass());
			Object[] params = new Object[0];

			boolean isFirst = true;

			StringBuilder strBuilder = new StringBuilder(512);
			strBuilder.append(getClass().getName()).append('{');
			for (PropertyDescriptor descriptor : props) {
				Method m = descriptor.getReadMethod();
				if (m != null) {
					boolean accessible = m.isAccessible();
					if (!accessible) {
						m.setAccessible(true);
					}
					try {
						Secret secretAnnotation = (Secret) m.getAnnotation(Secret.class);
						if (secretAnnotation != null) {
							continue;
						}
						Object result = m.invoke(this, params);
						if (!isFirst) {
							strBuilder.append(", ");
						} else {
							isFirst = false;
						}
						strBuilder.append(descriptor.getName()).append(": ");
						if ((result instanceof String)) {
							strBuilder.append('"');
							strBuilder.append(result);
							strBuilder.append('"');
						} else {
							strBuilder.append(result);
						}
					} catch (Exception e) {
					}
					if (!accessible) {
						m.setAccessible(false);
					}
				}
			}
			strBuilder.append('}');

			return strBuilder.toString();
		} catch (Exception e) {
		}
		return super.toString();
	}

	public String toString() {
		if (this.visitor.get() == null) {
			this.visitor.set(this);
			try {
				return toString0();
			} finally {
				this.visitor.set(null);
			}
		}
		return this.simpleName + "@" + Integer.toHexString(hashCode());
	}
}
