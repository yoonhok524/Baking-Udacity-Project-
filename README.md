# Baking

This is project for [Udacity Android nanodegree](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801)
----


This app fetch some recipe data from Udacity network. (https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json).<br />
This data is given by Udacity.

It shows the recipe in multi screen like mobile phone and tablet.<br />
To implement this, I refer to the android document (https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json)<br />
![Tablet](https://lh3.googleusercontent.com/28qvGWhChPbyuT7Dhz8zelgjHj1nJciT7vZUc4vbSBcz9-pEv-yGM5CyQe0tprODnxMOylLJA6keNSjbLIP-OjGp2c3KZNBkcuLmf7L0X2WAgL9O7I5gaZx3H8K_PZD9eXZy4La_Q8OhrtGnlHsLkQyHpCJ6foEI29tBuD9lT5W2OxxcZA_YTK03vE2EFp_lwWvzT3mUiA0BLPT4iqcxPsy825ZTkCeC8y-HXx6F3623KHHg6PhfEjuIPqsFYeRrNXo-VldF5fgTW-rKxo754KLBnb07anbmlZRev3sz4fj2KV1b1cUe0959VILZbUnxJKWdVVQGhNxXgLK-nnFjWF6eyyuchnQ7OGdphNEwEDSk8QChB6GDhW6q_Sj64wfqpufB8NeiuMZhzRAyliBrU-r_KVw6LoPlX31jm-zJgfxdPSw3Ylj7In6izd9pGZezYp7JblQ9_Z9qr7QvEYHhS8HT_ckL88kvQuqFKzlJF3ot04oKKGTUPW7HOywju8v1US_u1cJjzrGbfJ4jGZyL3IRhIVbPFNzim8e-3ivvmkWOXhAopH-e8CzzHO54d7nrplGubJLvYQBf0HlF7NcOXVdNT8ScBiXHQiV4Rb6Y88D0CXI=w1559-h974-no)

You can select one of recipe and check ingredients and steps.<br />
To make below screen, I used ListFragment.<br />
![Tablet](https://lh3.googleusercontent.com/q0lM_vQXu6Iy1KHgmqdJXFrUH16frBiNRihDllyd7Bi70rE-pJDUQdwrGalJkhON0fLIcTM02xdg3HNGuobRXADs5TtH9BDPaVvXZrvSzVkmsnoxkt_RqwkRu4IWGT1rAvZ684ot38PqtMy6B1tAzjWvAI2YAO3xLMBXxX6qwzMvFAsk4af8j4TCGm_2Qur3FtSEpxtVsHPotuvRNma4l8IcLE2ZvBDH9ZVjTtj8MCYuHqcu2CjjhGPLlhUkeyXnxh2h9Dno94jamKYklv4hzNHgOXCQISyf_hVNzh39RzXUlgOwHLkPRC4rPB_v3BT8rw-pE7zsCSFdRjjKk-Hz9t0WAioZ_B7WhEf02HpOEXtA2OU0GYQN7Wge62M52gbJmdbxAAVPPbsa6LUuKYb-iO0UVozhv9ZRZsvgC64kWllTYrbvMujemmukZdn_SwVmwEjbBwXS4oaZGyNpifq3D8Qo4nd-tXxNLCOdZXonq8o8_CtWXMzWBHC7cmK7i1Aq2BdcA9ZZ-M_0AN9r7KTSVpgZdFCuo1ba-lwn2cTzxbfDWBkrhvBJTY0aV9KuJnj3AgFrb7Vuzt4IxvUO8x_xivfMdcrtY0N61aondIaZAjxo1pk=w1559-h974-no)

You can see any video in detail step view.<br />
ExoPlayer Library is used to show the video in android.<br />
This is very simple example to use ExoPlayer.<br />
![Tablet](https://lh3.googleusercontent.com/fiaGBH9ti-MKKNSPs4azJlXZu3DfRVizbPzIwPUFfih7zg3RhQ8m55KleVY5StKIxSBR5xKdwOceJnJVt3Ggu7WWqhDf9zjWMTYz8Jv_cT6CbPO-Q3OHlXQS3H7jpDx7BgkGYN2bVKSGr0OU02ld8TuYRURe6j27oooVXoZ2VWGscB-f-3wJ_airt9s_D66nWEfH_25lVrSDJMGCpQ-Wk74Mc0k_yQlHBB4mTaOf0Fdy6Ruhp-DPTOXGzC1QycnxFxNn6j3Yp3hOFDzgsLL8Y8ApBSi18GzLMSNf-29rzDPsEtQX1b9nyLFcNweLE5KOuRp55C02gNU9by_QlHhaQHl8uPWsqyTmScLU9VXDpuuFm013hGBXWq8pKUfI43ZSc93vqIBko61aWkSH3bA7y-mAI6p2qmsYaE2h57cZxaBGEoYepkyvQ_bHgq6zzYI06nxT-m9biUvKbvqcwlpogc93ozcRO7QTa22B3wXhG5H9-KtHAe82RXWTK-3x-64OapkD9tIDlJdXI7QwYcCZonHaf4BljRHoEcW_gEbHRKsZ9nEcrCscumOQ9LCL_2nELOK3Sguu1m8gT8Nd1-A3nuDofVgbm4pzNCAzToKhQG8tKtE=w1559-h974-no)

And you can create widget in home screen, so you can see the ingredients easily.<br/>
My widget is showing ingredients as collection data.<br />
So I implemented RemoteviewsService and RemoteViewsFactory.<br />
You can refer to the guide in here: (https://developer.android.com/guide/topics/appwidgets/index.html#collections) <br/>
![Tablet](https://lh3.googleusercontent.com/ZIDpPSLz0nQoDpBY_7rMADmDRd0aB3wKr1JrSFxeFA0TJ6PzS8E2WlFBuMZxjYspYVUpEDEt7Wz7um-J_q7itjtnTwubyjnfYlOtyKuMjviATBLa86LyLnfCIv1iJPUcy7qKAQ17uzO-gtHJKh_es8L0-RwxdLRQd6DgWc-OEmoawVR6QLH8rHj0DWrUM6TqYaErAp9n7G5DtdYfSvHPNfqmnbsUFeJI7rpge_XHR_-LCwVILGhTsQzIulAoeUDbC0rExtwcYLQE-kQRGViNP1phToBFuLiEDMLmy3oi0VxHfFiB31tvGE8E4gc7jiz0jLr4KoT4ZoKIJhh4wCqI9ACxwzIbDyTOmS0hWb64orBnpv9dvHum76CQSjmGF8-nbDR5GxxD1d0FgxgRCskTcPbc-aa82TK9ugpfGsHw1rFU_4Sxd1DYjgTAXSi2QA3gFd6PHRgZ65wFcqeyXoH1aiYka6muMsyk2j2zvQT6dczEwPMWhXOJ3EOkip8TVfKIro4xXKpGoJ63ujS51k-JTLQUZKQeLfCiv8ls9cbv5ScCRvfvozzNtVWU0hJZxlEcWt31eoc1p9MdhUeTym74-ZtEjBh6eVchX6ix1DwNNF5BPyw=w1549-h974-no)

<br/>
This app is  
  - using REST API to get the recipet data from Udacity network.<br/>
  - using Glide Image Library to show the Recipe image. (But actually there are empty url string in the recipe data from Udacity network, so it doesn't show any images.)<br/>
  - using ExoPlayer video Library to show the cook video.
  - creating App Widget<br/>
  - support multi screen (Mobile hand device, Large screen tablet)<br/>
  - containing simple espresso test<br/>
