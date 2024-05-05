# Deadline

Modify this file to satisfy a submission requirement related to the project
deadline. Please keep this file organized using Markdown. If you click on
this file in your GitHub repository website, then you will see that the
Markdown is transformed into nice-looking HTML.

## Part 1.1: App Description

> The app is designed to generate a joke based on user input. If the user enters
> nothing, then a random joke will be made. The joke will then be translated into
> the way that Yoda speaks. The primary functions of this app are the text field,
> the "Get Joke" button, and the "Yoda Translate" button.
>
> The "Get Joke" button generates a joke from JokeAPI either randomly or based upon
> what the user inputted. The joke will be printed directly under this button.
>
> The "Yoda Translate" button uses the printed-out joke to send a request to the Yoda
> Translator API and prints out a Yoda-spoken version of the joke below the "Yoda
> Translate" button.

> **Also, include the GitHub `https` URL to your repository.**
> https://github.com/m-yguy/cs1302-str-list-test.git

TODO WRITE / REPLACE

## Part 1.2: APIs

> For each RESTful JSON API that your app uses (at least two are required),
> include an example URL for a typical request made by your app. If you
> need to include additional notes (e.g., regarding API keys or rate
> limits), then you can do that below the URL/URI. Placeholders for this
> information are provided below. If your app uses more than two RESTful
> JSON APIs, then include them with similar formatting.

### API 1

```
https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single
    The above URL is a typical request for a random joke, when the user inputs nothing into the text field.

https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single&contains=java
    The above URL is a typical request for generating a joke related to the word "java"
    The addition of "&contains=userInput" at the endpoint of the URL is what differs from a randomized joke.
```

> Replace this line with notes (if needed) or remove it (if not needed).

### API 2

```
https://api.funtranslations.com/translate/yoda.json?text=joke
    The joke variable inside "?text=joke" is replaced with an encoded version of a joke.
```

> Example of an encoded Yoda Response:
    https://api.funtranslations.com/translate/yoda.json?text=Master%20Obiwan%20has%20lost%20a%20planet.
    The text after "?text=" is encoded and used to create the URL for a Yoda Translated JSON Response.

## Part 2: New

> I learned how vast the number of APIs are out there. When I saw the link of all the APIs, I was shocked.
> The new things I learned are that I can use APIs to build any app I want.

TODO WRITE / REPLACE

## Part 3: Retrospect

> I might have approached this project differently by constructing my scene graph hierarchy first before
> jumping right in to coding. I should have laid out the scene graph on paper first, and that would have made
> my coding process easier.

TODO WRITE / REPLACE
