package timu7;
//IGraph½Ó¿Ú£º

public interface IGraph {
	void createGraph();
	int getVexNum();
	int getArcNum();
	Object getVex(int v);
	int locateVex(Object vex);
	int firstAdjVex(int v);
	int nextAdjVex(int v,int w);
	void addArc();
	void deleteArc();
	void addVex();
	void deleteVex();
	void getDegree();
	void display();

}