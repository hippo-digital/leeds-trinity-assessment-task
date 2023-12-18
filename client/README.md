# Instructions

## Installation

- Clone the repository on your local machine: [like this](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository)
- Install node LTS from [here](https://nodejs.org)
- Open the cloned repository in a terminal e.g. `cd leeds-trinity-assessment-task/client`
- Run `npm i` to install the `node_modules` folder

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

Then there are a couple more places where the code need changing:

`foodApi.getFoodsByOutlet(1.2).then(data => console.log(data));`

This code needs changing to read:

`foodApi.getFoodsByOutlet().then(data => console.log(data));`

This line should be commented out or removed: 

```
const url = `foods/v1/${outletId}/${queryString}`;
```

This line should be uncommented to take its place:

```
//const url = `foods/v1/${outletId}${queryString ? '/' : ''}${queryString}`;
```

Now you'll need to set an environment variable to point at the remote API. In a real environment you might well have to ask around to find out the value for this, although in an ideal world it would be either documented or made available to you as soon as you start on a project. In practice, particularly in consultancy or contracting work, you'll often have to ask multiple people, many of whom might be busy, stressed out or force you through a complex process to get this kind of information. Here, we will give you the URL you need.

## Setting environment variables

### MacOS and Ubuntu Linux

Type `export TRINITY_BASE_PATH=http://35.195.182.8:8081/task-api/` into the terminal.

This will set the environment variable temporarily, which is all you need right now. Make sure to do this in the same terminal you run the solution from or it will not work.

## Running Solution

Type `npm run start` into the terminal and if everything is working, you should receive the following text within the output:

- Get outlets call completed
- Get foods by outlet call completed
- Get foods call completed

If for any reason it's not possible to get to this stage, please ask for assistance.


