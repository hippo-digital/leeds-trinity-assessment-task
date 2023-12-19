# Instructions

## Installation

- Clone the repository on your local machine: [like this](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository)
- Install node LTS from [here](https://nodejs.org)
- Open the cloned repository in a terminal e.g. `cd leeds-trinity-assessment-task/client`
- Run `npm i`

## Bug Fixing

Fix the following code issues to get the repo working:

`foodApi.getOutlets(null).then(data => console.log(data));`

Change this to:

`foodApi.getOutlets({}).then(data => console.log(data));`

Next change:

```
var config = {
        method: 'PUT'
    };
```

This should be:

```
var config = {
        method: 'GET'
    };
```

Then there are a couple more places where the code needs changing:

`foodApi.getFoodsByOutlet(1.2).then(data => console.log(data));`

This code needs changing to read:

`foodApi.getFoodsByOutlet(1).then(data => console.log(data));`

This line should be commented out or removed: 

```
const url = `foods/v1/${outletId}/${queryString}`;
```

This line should be uncommented to take its place:

```
//const url = `foods/v1/${outletId}${queryString ? '/' : ''}${queryString}`;
```

Please note that these are not necessarily always the best ways to fix the problems and in some cases could be papering over other issues.
If you look in the code comments you'll find some hints about what else might be wrong or better ways to fix things. As such, feel free
to deviate from the above instructions if you are sure you know a better way.

## Setting Environment Variables

You'll need to set an environment variable to point at the remote API. In a real environment you might well have to ask around to find out the value for this, although in an ideal world it would be either documented or made available to you as soon as you start on a project. In practice, particularly in consultancy or contracting work, you'll often have to ask multiple people, many of whom might be busy, stressed out or force you through a complex process to get this kind of information. Here, we will give you the URL you need.

### MacOS and Ubuntu Linux

Type `export BASE_TRINITY_PATH=http://35.195.182.8:8081/task-api/` into the terminal.

To check this has worked, type `echo $BASE_TRINITY_PATH`. The output should be 

> http://35.195.182.8:8081/task-api/

This will set the environment variable temporarily, which is all you need right now. Make sure to do this in the same terminal you run the solution from or it will not work.

### Windows

For Windows we're going to do things a slightly different way by creating a `.env` file. This allows you to set environment variables in one place and to pass them to the Node program all at once, so you can setup your local working environment with pointers to the things your program will need that are specific to your computer, and may differ from integration and production systems. This method differs from the Mac/Linux methodology because we are setting these *only* for Node and not for the OS itself or the terminal.

If you look at the `.gitignore` file in the root directory, you'll notice we have set this file so it's not checked in to source control. This is a good practice.

To set your Node environment variable, first create a `.env file`, either in your IDE or by typing `notepad .env` from the command line. Add the following line `BASE_TRINITY_PATH=http://35.195.182.8:8081/task-api/` then save the file.

## Running Solution

Type `npm run start` into the terminal for Linux or Mac, or `npm run win-start` for Windows. If everything is working, you should receive the following text within the output:

### Outcome

- Get outlets call completed
- Get foods call completed
- Get foods by outlet call completed

If for any reason it's not possible to get to this stage, or you do not see this in the output, please ask for assistance.

## Extra Credit

Why doesn't the solution work when you run it with `node index.js` from the terminal? The `package.json` should give you a good idea why. With this in mind, would it be possible to run the solution without setting an environment variable at all? Could you figure out why we might prefer to do it this way?
