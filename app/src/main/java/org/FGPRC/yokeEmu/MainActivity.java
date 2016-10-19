package org.FGPRC.yokeEmu;

import android.os.*;
import android.view.*;
import android.content.pm.*;
import android.app.*;
import android.content.*;
import android.widget.*;

public class MainActivity extends Activity 
{
	YokeView my;
	private boolean isMainUI=true;
    private static FragmentManager mfm;
    private static YokeFragment yf;
    private static Fragment pref;
    private static Fragment setting;
    private static final String STRING_YOKEVIEW="YOKEVIEW";
    private static SharedPreferences sp;
    private final static int MainViewId ( )
    {
        return R.id.mainRelativeLayout1;
    };
    @Override
    public void onCreate ( Bundle savedInstanceState )
    {

        super.onCreate (savedInstanceState);
        ExceptionHandler.getInstance ().init (this);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		mfm = getFragmentManager ();
		setFullScreen ();
		//my=new YokeView(MainActivity.this);
        
        sp = getSharedPreferences ("Config", 0);
		setContentView(R.layout.main);
        yf = new YokeFragment ();
		mfm.beginTransaction().add(MainViewId(),new LogoFragment());
		try
			{
				Thread.sleep ( 1000 );
			}
		catch (InterruptedException e)
			{}
		mfm.beginTransaction ( ).replace ( MainViewId ( ), yf, STRING_YOKEVIEW ).commit ( );
        }
    public void setFullScreen ( )
    {
        getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
    @Override
    public boolean onKeyDown ( int keyCode, KeyEvent event )
    {if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (isMainUI)
            {
                AlertDialog.Builder adb=new AlertDialog.Builder (this);
                adb.setTitle ("Confirm")
                    .setMessage ("Confirm to exit?")
                    .setNegativeButton ("Cancel", null)
                    .setPositiveButton ("Exit", new DialogInterface.OnClickListener (){

                        @Override
                        public void onClick ( DialogInterface p1, int p2 )
                        {
                            android.os.Process.killProcess (android.os.Process.myPid ());
                            // TODO: Implement this method
                        }
                    })
                    .create ()
                    .show ();



                return true;
            }
            else
            {return super.onKeyDown (keyCode, event);}

        }
        // TODO: Implement this method
        return super.onKeyDown (keyCode, event);
    }

		
		private class LogoFragment extends Fragment
			{

				@Override
				public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
					{TextView t=new TextView(MainActivity.this);
					t.setBackgroundColor(0);
					t.setText("FG YOKE");
						// TODO: Implement this method
						return t;
					}
		
	}
}