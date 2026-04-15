package com.bn.util;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyPicFileChooser extends JFileChooser 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6693138001474504539L;

	public MyPicFileChooser()
	{
		this.setCurrentDirectory(null);
		this.removeChoosableFileFilter(this.getChoosableFileFilters()[0]);
		this.addChoosableFileFilter(new FileNameExtensionFilter("jpg","JPGͼƬ","JPG"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("png","pngͼƬ","PNG"));

	}
}