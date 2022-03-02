using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class mainmenu : MonoBehaviour
{

    public GameObject aboutusobj;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void launch()
    {
        SceneManager.LoadScene("content");
    }

    public void aboutus()
    {
        aboutusobj.SetActive(true);
    }

    public void quigame()
    {
        Application.Quit();
    }

    public void deer()
    {
        SceneManager.LoadScene("deer");
    }

    public void elephant()
    {
        SceneManager.LoadScene("elephant");
    }

    public void bacteria()
    {

    }

    public void backcontent()
    {
        SceneManager.LoadScene("content");
    }

    public void backmenu()
    {
        SceneManager.LoadScene("mainmenu");
    }



}
