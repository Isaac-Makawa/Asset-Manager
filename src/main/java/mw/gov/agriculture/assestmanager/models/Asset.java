/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mw.gov.agriculture.assestmanager.models;

/**
 *
 * @author USER
 */
public class Asset {
    int id,usefulLife;
    String name,description,acq_date,depreciation_method;
    boolean disposed;
    Department department;
    AssetCategory assetCategory;
    
    public Asset(){}
    
    

    public int getId() {
        return id;
    }

    public String getDepreciation_method() {
        return depreciation_method;
    }

    public void setDepreciation_method(String depreciation_method) {
        this.depreciation_method = depreciation_method;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(int usefulLife) {
        this.usefulLife = usefulLife;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcq_date() {
        return acq_date;
    }

    public void setAcq_date(String acq_date) {
        this.acq_date = acq_date;
    }

    public boolean isDisposed() {
        return disposed;
    }

    public void setDisposed(boolean disposed) {
        this.disposed = disposed;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public AssetCategory getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(AssetCategory assetCategory) {
        this.assetCategory = assetCategory;
    }
    
    
    
}
