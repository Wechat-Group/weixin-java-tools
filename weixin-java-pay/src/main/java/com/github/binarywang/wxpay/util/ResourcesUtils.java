package com.github.binarywang.wxpay.util;

import jodd.io.IOUtil;
import jodd.util.ClassUtil;
import lombok.val;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 基于jodd.util.ResourcesUtil改造实现
 * @author jodd
 */
public class ResourcesUtils {

	/**
	 * Retrieves given resource as URL.
	 * @see #getResourceUrl(String, ClassLoader)
	 */
	public static URL getResourceUrl(final String resourceName) {
		return getResourceUrl(resourceName, null);
	}

	/**
	 * Retrieves given resource as URL. Resource is always absolute and may
	 * starts with a slash character.
	 * <p>
	 * Resource will be loaded using class loaders in the following order:
	 * <ul>
	 * <li>{@link Thread#getContextClassLoader() Thread.currentThread().getContextClassLoader()}</li>
	 * <li>{@link Class#getClassLoader() ClassLoaderUtil.class.getClassLoader()}</li>
	 * <li>if <code>callingClass</code> is provided: {@link Class#getClassLoader() callingClass.getClassLoader()}</li>
	 * </ul>
	 */
	public static URL getResourceUrl(String resourceName, final ClassLoader classLoader) {

		if (resourceName.startsWith("/")) {
			resourceName = resourceName.substring(1);
		}

		URL resourceUrl;

		// try #1 - using provided class loader
		if (classLoader != null) {
			resourceUrl = classLoader.getResource(resourceName);
			if (resourceUrl != null) {
				return resourceUrl;
			}
		}

		// try #2 - using thread class loader
		final ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
		if ((currentThreadClassLoader != null) && (currentThreadClassLoader != classLoader)) {
			resourceUrl = currentThreadClassLoader.getResource(resourceName);
			if (resourceUrl != null) {
				return resourceUrl;
			}
		}

		// try #3 - using caller classloader, similar as Class.forName()
    val callerClass = ClassUtil.getCallerClass(2);
    val callerClassLoader = callerClass.getClassLoader();

		if ((callerClassLoader != classLoader) && (callerClassLoader != currentThreadClassLoader)) {
			resourceUrl = callerClassLoader.getResource(resourceName);
      return resourceUrl;
		}

		return null;
	}

	public static String getResourceAsString(final String resourceName) throws IOException {
		final InputStream inputStream = getResourceAsStream(resourceName);
		try {
			final char[] data = IOUtil.readChars(inputStream);
			return new String(data);
		}
		finally {
			IOUtil.close(inputStream);
		}
	}

	/**
	 * Opens a resource of the specified name for reading.
	 * @see #getResourceAsStream(String, ClassLoader)
	 */
	public static InputStream getResourceAsStream(final String resourceName) throws IOException {
		return getResourceAsStream(resourceName, null);
	}

	/**
	 * Opens a resource of the specified name for reading.
	 * @see #getResourceUrl(String, ClassLoader)
	 */
	public static InputStream getResourceAsStream(final String resourceName, final ClassLoader callingClass) throws IOException {
		final URL url = getResourceUrl(resourceName, callingClass);
		if (url != null) {
			return url.openStream();
		}
		return null;
	}

	/**
	 * Opens a resource of the specified name for reading. Controls caching,
	 * that is important when the same jar is reloaded using custom classloader.
	 */
	public static InputStream getResourceAsStream(final String resourceName, final ClassLoader callingClass,
                                                final boolean useCache) throws IOException {
		final URL url = getResourceUrl(resourceName, callingClass);
		if (url != null) {
			final URLConnection urlConnection = url.openConnection();
			urlConnection.setUseCaches(useCache);
			return urlConnection.getInputStream();
		}
		return null;
	}

}
