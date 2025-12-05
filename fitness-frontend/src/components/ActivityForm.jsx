import { Box, Button, duration, FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material'
import React, { useState } from 'react'
import { addActivity } from '../services/api';

const ActivityForm = ({onActivitesAdded}) => {

  const[activity,setActivity]=useState({
    type:"RUNNING",duration:'',caloriesBurned:'',
    additionalMetrics:{}
  });

  const handleSubmit=async(e)=>{
    e.preventDefault();
    try{
await addActivity(activity);
onActivitesAdded();
setActivity({
    type:"RUNNING",duration:'',caloriesBurned:'',
    additionalMetrics:{}
  });
    }catch(error){
      console.error(error);
    }
  }

  return (
    <Box component="form" sx={{mb:2}} onSubmit={handleSubmit}>
      <FormControl fullWidth sx={{mb:2}}>
  <InputLabel>Acitvity Type</InputLabel>
  <Select
    value={activity.type}
    label="Activity Type"
    onChange={(e)=>{
      setActivity({...activity,type: e.target.value})
    }}
  >
    <MenuItem value="RUNNING">RUNNING</MenuItem>
    <MenuItem value="WALKING">WALKING</MenuItem>
    <MenuItem value="CYCLING">CYCLING</MenuItem>
  </Select>
</FormControl>

<TextField fullWidth
label="Duration(Minutes)"
type="number"
sx={{mb:2}}
value={activity.duration}
onChange={(e)=>setActivity({...activity,duration:e.target.value})}/>

<TextField fullWidth
label="Calories Burned"
type="number"
sx={{mb:2}}
value={activity.caloriesBurned}
onChange={(e)=>setActivity({...activity,caloriesBurned:e.target.value})}/>

<Button type='submit' variant='contained'>
  Add Acitvity
</Button>

    </Box>
  )
}

export default ActivityForm
