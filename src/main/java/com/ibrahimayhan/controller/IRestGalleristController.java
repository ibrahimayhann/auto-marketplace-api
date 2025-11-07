package com.ibrahimayhan.controller;

import com.ibrahimayhan.dto.DtoGallerist;
import com.ibrahimayhan.dto.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> savegallerist(DtoGalleristIU input);
}
